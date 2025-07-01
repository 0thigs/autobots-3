package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.VeiculoControle;
import com.autobots.automanager.entidades.Veiculo;

@Service
public class AdicionarLinkVeiculo implements AdicionadorLink<Veiculo> {

  @Override
  public void adicionarLink(Set<Veiculo> veiculos) {
    for (Veiculo veiculo : veiculos) {
      adicionarLink(veiculo);
    }
  }

  @Override
  public void adicionarLink(Veiculo veiculo) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VeiculoControle.class).obterVeiculo(veiculo.getId()))
        .withRel("obter-veiculo");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VeiculoControle.class).listarVeiculos())
        .withRel("obter-todos-veiculos");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VeiculoControle.class).criarVeiculo(null))
        .withRel("cadastrar-veiculo");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VeiculoControle.class).excluirVeiculo(veiculo.getId()))
        .withRel("excluir-veiculo");

    veiculo.add(linkProprio, linkTodos, linkCriar, linkExcluir);
  }
}
