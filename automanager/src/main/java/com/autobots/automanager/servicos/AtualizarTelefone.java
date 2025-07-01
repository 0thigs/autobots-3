package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Telefone;

@Component
public class AtualizarTelefone {
  @Autowired
  private VerificarStringNula verificarString;

  public void atualizar(Telefone telefone, Telefone atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getDdd())) {
        telefone.setDdd(atualizado.getDdd());
      }
      if (!verificarString.verificar(atualizado.getNumero())) {
        telefone.setNumero(atualizado.getNumero());
      }
    }
  }

  public void atualizar(Set<Telefone> telefones, Set<Telefone> atualizados) {
    for (Telefone atualizado : atualizados) {
      for (Telefone telefone : telefones) {
        if (atualizado.getId() != null && atualizado.getId().equals(telefone.getId())) {
          atualizar(telefone, atualizado);
        }
      }
    }
  }
}
