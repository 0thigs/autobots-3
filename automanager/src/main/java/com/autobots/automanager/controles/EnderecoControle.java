package com.autobots.automanager.controles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.repositorios.EnderecoRepositorio;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkEndereco;
import com.autobots.automanager.servicos.AtualizarEndereco;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Endereço", description = "Operações CRUD para endereços")
public class EnderecoControle {

  @Autowired
  private EnderecoRepositorio repositorioEndereco;

  @Autowired
  private UsuarioRepositorio repositorioUsuario;

  @Autowired
  private AdicionarLinkEndereco servicoAdicionarLink;

  @Autowired
  private AtualizarEndereco servicoAtualizar;

  @PostMapping("/endereco/cadastrar")
  @Operation(summary = "Cadastrar novo endereço")
  public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco) {
    if (repositorioEndereco.findById(endereco.getId()).isPresent()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    repositorioEndereco.save(endereco);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/enderecos")
  @Operation(summary = "Listar todos os endereços")
  public ResponseEntity<List<Endereco>> listarEnderecos() {
    List<Endereco> enderecos = repositorioEndereco.findAll();
    if (enderecos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(new HashSet<>(enderecos));
    return new ResponseEntity<>(enderecos, HttpStatus.OK);
  }

  @GetMapping("/endereco/{id}")
  @Operation(summary = "Buscar endereço por ID")
  public ResponseEntity<Endereco> buscarEndereco(@PathVariable long id) {
    Optional<Endereco> endereco = repositorioEndereco.findById(id);
    if (endereco.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(endereco.get());
    return new ResponseEntity<>(endereco.get(), HttpStatus.OK);
  }

  @PutMapping("/endereco/atualizar")
  @Operation(summary = "Atualizar endereço")
  public ResponseEntity<?> atualizarEndereco(@RequestBody Endereco atualizado) {
    Optional<Endereco> existente = repositorioEndereco.findById(atualizado.getId());
    if (existente.isPresent()) {
      servicoAtualizar.atualizar(existente.get(), atualizado);
      repositorioEndereco.save(existente.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/endereco/remover")
  @Operation(summary = "Remover endereço")
  public ResponseEntity<?> removerEndereco(@RequestBody Endereco paraRemover) {
    Optional<Endereco> endereco = repositorioEndereco.findById(paraRemover.getId());
    if (endereco.isPresent()) {
      repositorioUsuario.findByEndereco(endereco.get()).ifPresent(usuario -> {
        usuario.setEndereco(null);
        repositorioUsuario.save(usuario);
      });
      repositorioEndereco.delete(endereco.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
