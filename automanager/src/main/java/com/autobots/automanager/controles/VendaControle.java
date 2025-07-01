package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.VendaRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkVenda;
import com.autobots.automanager.servicos.AtualizarVenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/venda")
public class VendaControle {

  @Autowired
  private VendaRepositorio vendaRepositorio;

  @Autowired
  private EmpresaRepositorio empresaRepositorio;

  @Autowired
  private AtualizarVenda atualizadorVenda;

  @Autowired
  private AdicionarLinkVenda adicionadorLink;

  @GetMapping("/listar")
  public ResponseEntity<List<Venda>> listarVendas() {
    List<Venda> vendas = vendaRepositorio.findAll();

    if (vendas.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    adicionadorLink.adicionarLink(new HashSet<>(vendas));
    return ResponseEntity.ok(vendas);
  }

  @PostMapping("/criar")
  public ResponseEntity<?> criarVenda(@RequestBody Venda venda) {
    if (venda.getEmpresa() == null || venda.getEmpresa().getId() == null) {
      return ResponseEntity.badRequest().body("Empresa é obrigatória");
    }

    Optional<Empresa> empresa = empresaRepositorio.findById(venda.getEmpresa().getId());
    if (empresa.isEmpty()) {
      return ResponseEntity.badRequest().body("Empresa não encontrada");
    }

    venda.setEmpresa(empresa.get());

    Venda novaVenda = vendaRepositorio.save(venda);
    return ResponseEntity.ok(novaVenda);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Venda> obterVenda(@PathVariable Long id) {
    return vendaRepositorio.findById(id)
        .map(venda -> {
          adicionadorLink.adicionarLink(venda);
          return ResponseEntity.ok(venda);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Venda> atualizarVenda(@PathVariable Long id, @RequestBody Venda dadosAtualizados) {
    return vendaRepositorio.findById(id)
        .map(venda -> {
          atualizadorVenda.atualizar(venda, dadosAtualizados);
          Venda vendaAtualizada = vendaRepositorio.save(venda);
          vendaAtualizada.getEmpresa().getRazaoSocial();
          adicionadorLink.adicionarLink(vendaAtualizada);
          return ResponseEntity.ok(vendaAtualizada);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
    Optional<Venda> venda = vendaRepositorio.findById(id);
    if (venda.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    vendaRepositorio.delete(venda.get());
    return ResponseEntity.noContent().build();
  }
}
