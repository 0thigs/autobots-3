package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.entidades.Endereco;

@Service
public class AdicionarLinkEndereco implements AdicionadorLink<Endereco> {

  @Override
  public void adicionarLink(Set<Endereco> enderecos) {
    for (Endereco endereco : enderecos) {
      adicionarLink(endereco);
    }
  }

  @Override
  public void adicionarLink(Endereco endereco) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(EnderecoControle.class)
            .buscarEndereco(endereco.getId()))
        .withRel("obter-endereco");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(EnderecoControle.class)
            .listarEnderecos())
        .withRel("obter-todos-enderecos");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(EnderecoControle.class)
            .cadastrarEndereco(null))
        .withRel("cadastrar-endereco");

    Link linkAtualizar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(EnderecoControle.class)
            .atualizarEndereco(null))
        .withRel("atualizar-endereco");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(EnderecoControle.class)
            .removerEndereco(null))
        .withRel("excluir-endereco");

    endereco.add(linkProprio, linkTodos, linkCriar, linkAtualizar, linkExcluir);
  }
}
