package com.autobots.automanager.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Usuario;

@Component
public class AtualizarUsuario {
  @Autowired
  private VerificarStringNula verificarString;

  @Autowired
  private AtualizarEndereco atualizarEndereco;

  @Autowired
  private AtualizarDocumento atualizarDocumento;

  @Autowired
  private AtualizarTelefone atualizarTelefone;

  public void atualizar(Usuario usuario, Usuario atualizado) {
    atualizarDados(usuario, atualizado);
    atualizarEndereco.atualizar(usuario.getEndereco(), atualizado.getEndereco());
    atualizarDocumento.atualizar(usuario.getDocumentos(), atualizado.getDocumentos());
    atualizarTelefone.atualizar(usuario.getTelefones(), atualizado.getTelefones());
  }

  private void atualizarDados(Usuario usuario, Usuario atualizado) {
    if (!verificarString.verificar(atualizado.getNome())) {
      usuario.setNome(atualizado.getNome());
    }
    if (!verificarString.verificar(atualizado.getNomeSocial())) {
      usuario.setNomeSocial(atualizado.getNomeSocial());
    }
  }
}
