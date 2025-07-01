package com.autobots.automanager.controles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkEmpresa;
import com.autobots.automanager.servicos.AtualizarEmpresa;

@RestController
public class EmpresaControle {

  @Autowired
  private EmpresaRepositorio repositorio;

  @Autowired
  private AdicionarLinkEmpresa servicoAdicionarLink;

  @Autowired
  private AtualizarEmpresa servicoAtualizar;

  @PostMapping("/empresa/cadastrar")
  public ResponseEntity<?> cadastrarEmpresa(@RequestBody Empresa empresa) {
    repositorio.save(empresa);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/empresas")
  public ResponseEntity<List<Empresa>> listarEmpresas() {
    List<Empresa> empresas = repositorio.findAll();
    if (empresas.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(new HashSet<>(empresas));
    return new ResponseEntity<>(empresas, HttpStatus.OK);
  }

  @GetMapping("/empresa/{id}")
  public ResponseEntity<Empresa> buscarEmpresa(@PathVariable long id) {
    Optional<Empresa> empresa = repositorio.findById(id);
    if (empresa.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    servicoAdicionarLink.adicionarLink(empresa.get());
    return new ResponseEntity<>(empresa.get(), HttpStatus.OK);
  }

  @PutMapping("/empresa/atualizar")
  public ResponseEntity<?> atualizarEmpresa(@RequestBody Empresa atualizada) {
    Optional<Empresa> existente = repositorio.findById(atualizada.getId());
    if (existente.isPresent()) {
      servicoAtualizar.atualizar(existente.get(), atualizada);
      repositorio.save(existente.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/empresa/remover")
  public ResponseEntity<?> removerEmpresa(@RequestBody Empresa empresa) {
    Optional<Empresa> encontrada = repositorio.findById(empresa.getId());
    if (encontrada.isPresent()) {
      repositorio.delete(encontrada.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
