package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Produto;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.ProdutoRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkProduto;
import com.autobots.automanager.servicos.AtualizarProduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercadoria")
public class ProdutoControle {

  @Autowired
  private ProdutoRepositorio repositorioProduto;

  @Autowired
  private EmpresaRepositorio repositorioEmpresa;

  @Autowired
  private AdicionarLinkProduto adicionadorLink;

  @Autowired
  private AtualizarProduto atualizadorProduto;

  @GetMapping("/listar")
  public ResponseEntity<List<Produto>> listarProdutos() {
    List<Produto> produtos = repositorioProduto.findAll();
    if (produtos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    adicionadorLink.adicionarLink(new HashSet<>(produtos));
    return ResponseEntity.ok(produtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Produto> obterProduto(@PathVariable Long id) {
    Optional<Produto> produto = repositorioProduto.findById(id);
    if (produto.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    adicionadorLink.adicionarLink(produto.get());
    return ResponseEntity.ok(produto.get());
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto) {
    if (produto.getEmpresa() == null || produto.getEmpresa().getId() == null) {
      return ResponseEntity.badRequest().body("Empresa obrigatória");
    }

    Optional<Empresa> empresaOptional = repositorioEmpresa.findById(produto.getEmpresa().getId());
    if (empresaOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("Empresa não encontrada");
    }

    produto.setEmpresa(empresaOptional.get());

    Produto novoProduto = repositorioProduto.save(produto);
    adicionadorLink.adicionarLink(novoProduto);
    return ResponseEntity.ok(novoProduto);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id,
      @RequestBody Produto dadosAtualizados) {
    Optional<Produto> produtoOptional = repositorioProduto.findById(id);
    if (produtoOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    Produto produto = produtoOptional.get();
    atualizadorProduto.atualizar(produto, dadosAtualizados);
    Produto produtoSalvo = repositorioProduto.save(produto);

    produtoSalvo.getEmpresa().getRazaoSocial();

    adicionadorLink.adicionarLink(produtoSalvo);
    return ResponseEntity.ok(produtoSalvo);
  }

  @DeleteMapping("/remover/{id}")
  public ResponseEntity<Void> removerProduto(@PathVariable Long id) {
    Optional<Produto> produto = repositorioProduto.findById(id);
    if (produto.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    repositorioProduto.delete(produto.get());
    return ResponseEntity.noContent().build();
  }
}
