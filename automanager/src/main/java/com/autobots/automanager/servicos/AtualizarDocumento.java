package com.autobots.automanager.servicos;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Documento;

@Service
public class AtualizarDocumento {
  @Autowired
  private VerificarStringNula verificarString;

  public void atualizar(Documento documento, Documento atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getTipo().toString())) {
        documento.setTipo(atualizado.getTipo());
      }
      if (!verificarString.verificar(atualizado.getNumero())) {
        documento.setNumero(atualizado.getNumero());
      }
    }
  }

  public void atualizar(Set<Documento> documentos, Set<Documento> atualizados) {
    for (Documento atualizado : atualizados) {
      for (Documento documento : documentos) {
        if (atualizado.getId() != null && atualizado.getId().equals(documento.getId())) {
          atualizar(documento, atualizado);
        }
      }
    }
  }
}
