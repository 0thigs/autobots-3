package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkTelefone;
import com.autobots.automanager.servicos.AtualizarTelefone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Telefone", description = "Operações de CRUD para telefones")
public class TelefoneControle {

  @Autowired
  private TelefoneRepositorio repositorio;

  @Autowired
  private AdicionarLinkTelefone adicionarLink;

  @Autowired
  private AtualizarTelefone atualizador;

  @PostMapping("/telefone/cadastrar")
  @Operation(summary = "Cadastrar telefone", description = "Cadastra um novo telefone")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Telefone cadastrado com sucesso"),
    @ApiResponse(responseCode = "409", description = "Telefone já cadastrado")
  })
  public ResponseEntity<?> cadastrarTelefone(@RequestBody Telefone telefone) {
    Optional<Telefone> existente = repositorio.findById(telefone.getId());
    if (existente.isPresent()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    repositorio.save(telefone);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/telefones")
  @Operation(summary = "Listar telefones", description = "Retorna todos os telefones cadastrados")
  public ResponseEntity<List<Telefone>> listarTelefones() {
    List<Telefone> telefones = repositorio.findAll();
    if (telefones.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    adicionarLink.adicionarLink(new HashSet<>(telefones));
    return new ResponseEntity<>(telefones, HttpStatus.OK);
  }

  @GetMapping("/telefone/{id}")
  @Operation(summary = "Buscar telefone", description = "Busca um telefone pelo ID")
  public ResponseEntity<Telefone> buscarTelefone(@PathVariable long id) {
    Optional<Telefone> telefone = repositorio.findById(id);
    if (telefone.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    adicionarLink.adicionarLink(telefone.get());
    return ResponseEntity.ok(telefone.get());
  }

  @PutMapping("/telefone/atualizar")
  @Operation(summary = "Atualizar telefone", description = "Atualiza os dados de um telefone")
  public ResponseEntity<?> atualizarTelefone(@RequestBody Telefone telefoneAtualizado) {
    Optional<Telefone> telefone = repositorio.findById(telefoneAtualizado.getId());
    if (telefone.isPresent()) {
      atualizador.atualizar(telefone.get(), telefoneAtualizado);
      repositorio.save(telefone.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/telefone/excluir")
  @Operation(summary = "Excluir telefone", description = "Remove um telefone do sistema")
  public ResponseEntity<?> excluirTelefone(@RequestBody Telefone telefoneParaExcluir) {
    Optional<Telefone> telefone = repositorio.findById(telefoneParaExcluir.getId());
    if (telefone.isPresent()) {
      repositorio.delete(telefone.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
