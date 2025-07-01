package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.VendaControle;
import com.autobots.automanager.entidades.Venda;

@Service
public class AdicionarLinkVenda implements AdicionadorLink<Venda> {

  @Override
  public void adicionarLink(Set<Venda> vendas) {
    for (Venda venda : vendas) {
      adicionarLink(venda);
    }
  }

  @Override
  public void adicionarLink(Venda venda) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(VendaControle.class).obterVenda(venda.getId()))
        .withRel("obter-venda");

    venda.add(linkProprio);
  }
}
