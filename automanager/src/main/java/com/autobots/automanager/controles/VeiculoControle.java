package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.VeiculoRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkVeiculo;
import com.autobots.automanager.servicos.AtualizarVeiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {

  @Autowired
  private VeiculoRepositorio veiculoRepositorio;

  @Autowired
  private EmpresaRepositorio empresaRepositorio;

  @Autowired
  private AdicionarLinkVeiculo adicionadorLink;

  @Autowired
  private AtualizarVeiculo atualizadorVeiculo;

  @PostMapping("/criar")
  public ResponseEntity<?> criarVeiculo(@RequestBody Veiculo veiculo) {
    if (veiculo.getEmpresa() == null || veiculo.getEmpresa().getId() == null) {
      return ResponseEntity.badRequest().body("Empresa é obrigatória");
    }

    Optional<Empresa> empresa = empresaRepositorio.findById(veiculo.getEmpresa().getId());
    if (empresa.isEmpty()) {
      return ResponseEntity.badRequest().body("Empresa não encontrada");
    }

    veiculo.setEmpresa(empresa.get());

    Veiculo novoVeiculo = veiculoRepositorio.save(veiculo);
    adicionadorLink.adicionarLink(novoVeiculo);
    return ResponseEntity.ok(novoVeiculo);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<Veiculo>> listarVeiculos() {
    List<Veiculo> veiculos = veiculoRepositorio.findAll();
    if (veiculos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    adicionadorLink.adicionarLink(new HashSet<>(veiculos));
    return ResponseEntity.ok(veiculos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Veiculo> obterVeiculo(@PathVariable Long id) {
    Optional<Veiculo> veiculo = veiculoRepositorio.findById(id);
    if (veiculo.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    adicionadorLink.adicionarLink(veiculo.get());
    return ResponseEntity.ok(veiculo.get());
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo dadosAtualizados) {
    Optional<Veiculo> veiculoOptional = veiculoRepositorio.findById(id);
    if (veiculoOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Veiculo veiculo = veiculoOptional.get();
    atualizadorVeiculo.atualizar(veiculo, dadosAtualizados);
    Veiculo veiculoAtualizado = veiculoRepositorio.save(veiculo);

    veiculoAtualizado.getEmpresa().getRazaoSocial();

    adicionadorLink.adicionarLink(veiculoAtualizado);
    return ResponseEntity.ok(veiculoAtualizado);
  }

  @DeleteMapping("/excluir/{id}")
  public ResponseEntity<Void> excluirVeiculo(@PathVariable Long id) {
    Optional<Veiculo> veiculo = veiculoRepositorio.findById(id);
    if (veiculo.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    veiculoRepositorio.delete(veiculo.get());
    return ResponseEntity.noContent().build();
  }
}
