package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.ServicoRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkServico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servico")
public class ServicoControle {

  @Autowired
  private ServicoRepositorio servicoRepositorio;

  @Autowired
  private EmpresaRepositorio empresaRepositorio;

  @Autowired
  private AdicionarLinkServico adicionadorLink;

  @PostMapping("/criar")
  public ResponseEntity<?> criarServico(@RequestBody Servico servico) {
    if (servico.getEmpresa() == null || servico.getEmpresa().getId() == null) {
      return ResponseEntity.badRequest().body("Empresa é obrigatória");
    }

    Optional<Empresa> empresa = empresaRepositorio.findById(servico.getEmpresa().getId());
    if (empresa.isEmpty()) {
      return ResponseEntity.badRequest().body("Empresa não encontrada");
    }

    servico.setEmpresa(empresa.get());

    Servico novoServico = servicoRepositorio.save(servico);
    adicionadorLink.adicionarLink(novoServico);
    return ResponseEntity.ok(novoServico);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<Servico>> listarServicos() {
    List<Servico> servicos = servicoRepositorio.findAll();
    if (servicos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    adicionadorLink.adicionarLink(new HashSet<>(servicos));
    return ResponseEntity.ok(servicos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Servico> obterServico(@PathVariable Long id) {
    Optional<Servico> servico = servicoRepositorio.findById(id);
    if (servico.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    adicionadorLink.adicionarLink(servico.get());
    return ResponseEntity.ok(servico.get());
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Servico> atualizarServico(@PathVariable Long id, @RequestBody Servico dadosAtualizados) {
    Optional<Servico> servicoOptional = servicoRepositorio.findById(id);
    if (servicoOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Servico servico = servicoOptional.get();
    servico.setNome(dadosAtualizados.getNome());
    servico.setDescricao(dadosAtualizados.getDescricao());
    servico.setPreco(dadosAtualizados.getPreco());
    Servico servicoAtualizado = servicoRepositorio.save(servico);

    servicoAtualizado.getEmpresa().getRazaoSocial();

    adicionadorLink.adicionarLink(servicoAtualizado);
    return ResponseEntity.ok(servicoAtualizado);
  }

  @DeleteMapping("/excluir/{id}")
  public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
    Optional<Servico> servico = servicoRepositorio.findById(id);
    if (servico.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    servicoRepositorio.delete(servico.get());
    return ResponseEntity.noContent().build();
  }
}
