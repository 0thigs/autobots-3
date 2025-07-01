package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.entidades.Documento;

@Service
public class AdicionarLinkDocumento implements AdicionadorLink<Documento> {

  @Override
  public void adicionarLink(Set<Documento> documentos) {
    for (Documento documento : documentos) {
      adicionarLink(documento);
    }
  }

  @Override
  public void adicionarLink(Documento documento) {
    Link linkProprio = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentoControle.class)
            .buscarDocumento(documento.getId()))
        .withRel("obter-documento");

    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentoControle.class)
            .listarDocumentos())
        .withRel("obter-todos-documentos");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentoControle.class)
            .cadastrarDocumento(null))
        .withRel("cadastrar-documento");

    Link linkAtualizar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentoControle.class)
            .atualizarDocumento(null))
        .withRel("atualizar-documento");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder
            .methodOn(DocumentoControle.class)
            .removerDocumento(null))
        .withRel("excluir-documento");

    documento.add(linkProprio, linkTodos, linkCriar, linkAtualizar, linkExcluir);
  }
}
