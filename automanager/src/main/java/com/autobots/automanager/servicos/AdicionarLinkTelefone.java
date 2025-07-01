package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.entidades.Telefone;

@Service
public class AdicionarLinkTelefone implements AdicionadorLink<Telefone> {

  @Override
  public void adicionarLink(Set<Telefone> telefones) {
    for (Telefone telefone : telefones) {
      adicionarLink(telefone);
    }
  }

  @Override
  public void adicionarLink(Telefone telefone) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).buscarTelefone(telefone.getId()))
        .withRel("obter-telefone");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).listarTelefones())
        .withRel("obter-todos-telefones");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).cadastrarTelefone(null))
        .withRel("cadastrar-telefone");

    Link linkAtualizar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).atualizarTelefone(null))
        .withRel("atualizar-telefone");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).excluirTelefone(null))
        .withRel("excluir-telefone");

    telefone.add(linkProprio, linkTodos, linkCriar, linkAtualizar, linkExcluir);
  }
}
