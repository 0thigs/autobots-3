package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.ProdutoControle;
import com.autobots.automanager.entidades.Produto;

@Service
public class AdicionarLinkProduto implements AdicionadorLink<Produto> {

  @Override
  public void adicionarLink(Set<Produto> produtos) {
    for (Produto produto : produtos) {
      adicionarLink(produto);
    }
  }

  @Override
  public void adicionarLink(Produto produto) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ProdutoControle.class).obterProduto(produto.getId()))
        .withRel("obter-produto");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ProdutoControle.class).listarProdutos())
        .withRel("obter-todos-produtos");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ProdutoControle.class).cadastrarProduto(null))
        .withRel("cadastrar-produto");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ProdutoControle.class).removerProduto(null))
        .withRel("excluir-produto");

    produto.add(linkProprio, linkTodos, linkCriar, linkExcluir);
  }
}
