package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.ServicoControle;
import com.autobots.automanager.entidades.Servico;

@Service
public class AdicionarLinkServico implements AdicionadorLink<Servico> {

  @Override
  public void adicionarLink(Set<Servico> servicos) {
    for (Servico servico : servicos) {
      adicionarLink(servico);
    }
  }

  @Override
  public void adicionarLink(Servico servico) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServicoControle.class).obterServico(servico.getId()))
        .withRel("obter-servico");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServicoControle.class).listarServicos())
        .withRel("obter-todos-servicos");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServicoControle.class).criarServico(null))
        .withRel("cadastrar-servico");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(ServicoControle.class).excluirServico(servico.getId()))
        .withRel("excluir-servico");

    servico.add(linkProprio, linkTodos, linkCriar, linkExcluir);
  }
}
