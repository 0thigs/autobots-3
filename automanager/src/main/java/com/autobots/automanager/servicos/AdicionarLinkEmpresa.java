package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.EmpresaControle;
import com.autobots.automanager.entidades.Empresa;

@Service
public class AdicionarLinkEmpresa implements AdicionadorLink<Empresa> {

  @Override
  public void adicionarLink(Set<Empresa> empresas) {
    for (Empresa empresa : empresas) {
      adicionarLink(empresa);
    }
  }

  @Override
  public void adicionarLink(Empresa empresa) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmpresaControle.class).buscarEmpresa(empresa.getId()))
        .withRel("obter-empresa");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmpresaControle.class).listarEmpresas())
        .withRel("obter-todas-empresas");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmpresaControle.class).cadastrarEmpresa(null))
        .withRel("cadastrar-empresa");

    Link linkAtualizar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmpresaControle.class).atualizarEmpresa(null))
        .withRel("atualizar-empresa");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(EmpresaControle.class).removerEmpresa(null))
        .withRel("excluir-empresa");

    empresa.add(linkProprio, linkTodos, linkCriar, linkAtualizar, linkExcluir);
  }
}
