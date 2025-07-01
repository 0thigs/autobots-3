package com.autobots.automanager.controles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkDocumento;
import com.autobots.automanager.servicos.AtualizarDocumento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Documento", description = "Operações CRUD para documentos")
public class DocumentoControle {

  @Autowired
  private DocumentoRepositorio repositorio;

  @Autowired
  private AdicionarLinkDocumento servicoAdicionarLink;

  @Autowired
  private AtualizarDocumento servicoAtualizar;

  @PostMapping("/documento/cadastrar")
  @Operation(summary = "Cadastrar um novo documento")
  public ResponseEntity<?> cadastrarDocumento(@RequestBody Documento documento) {
    Optional<Documento> existente = repositorio.findById(documento.getId());
    if (existente.isPresent() || repositorio.findByNumero(documento.getNumero()).isPresent()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    repositorio.save(documento);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/documentos")
  @Operation(summary = "Listar todos os documentos")
  public ResponseEntity<List<Documento>> listarDocumentos() {
    List<Documento> documentos = repositorio.findAll();
    if (documentos.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(new HashSet<>(documentos));
    return new ResponseEntity<>(documentos, HttpStatus.OK);
  }

  @GetMapping("/documento/{id}")
  @Operation(summary = "Buscar documento por ID")
  public ResponseEntity<Documento> buscarDocumento(@PathVariable long id) {
    Optional<Documento> documento = repositorio.findById(id);
    if (documento.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(documento.get());
    return new ResponseEntity<>(documento.get(), HttpStatus.OK);
  }

  @PutMapping("/documento/atualizar")
  @Operation(summary = "Atualizar um documento")
  public ResponseEntity<?> atualizarDocumento(@RequestBody Documento atualizado) {
    Optional<Documento> existente = repositorio.findById(atualizado.getId());
    if (existente.isPresent()) {
      servicoAtualizar.atualizar(existente.get(), atualizado);
      repositorio.save(existente.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/documento/remover")
  @Operation(summary = "Remover um documento")
  public ResponseEntity<?> removerDocumento(@RequestBody Documento paraRemover) {
    Optional<Documento> documento = repositorio.findById(paraRemover.getId());
    if (documento.isPresent()) {
      repositorio.delete(documento.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
