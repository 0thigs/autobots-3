package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.UsuarioControle;
import com.autobots.automanager.entidades.Usuario;

@Service
public class AdicionarLinkUsuario implements AdicionadorLink<Usuario> {

  @Override
  public void adicionarLink(Set<Usuario> usuarios) {
    for (Usuario usuario : usuarios) {
      adicionarLink(usuario);
    }
  }

  @Override
  public void adicionarLink(Usuario usuario) {
    Link linkTodos = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UsuarioControle.class).listarUsuarios())
        .withRel("obter-todos-usuarios");

    Link linkCriar = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UsuarioControle.class).criarUsuario(null))
        .withRel("cadastrar-usuario");

    Link linkExcluir = WebMvcLinkBuilder
        .linkTo(WebMvcLinkBuilder.methodOn(UsuarioControle.class).excluirUsuario(usuario.getId()))
        .withRel("excluir-usuario");

    usuario.add(linkTodos, linkCriar, linkExcluir);
  }
}
