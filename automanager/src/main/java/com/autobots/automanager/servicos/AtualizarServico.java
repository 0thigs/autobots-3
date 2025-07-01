package com.autobots.automanager.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Servico;

@Service
public class AtualizarServico {
  @Autowired
  private VerificarStringNula verificarString;
  @Autowired
  private VerificarDoubleNulo verificarDouble;

  public void atualizar(Servico servico, Servico atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getNome())) {
        servico.setNome(atualizado.getNome());
      }
      if (!verificarDouble.verificar(atualizado.getPreco())) {
        servico.setPreco(atualizado.getPreco());
      }
      if (!verificarString.verificar(atualizado.getDescricao())) {
        servico.setDescricao(atualizado.getDescricao());
      }
    }
  }

  public void atualizar(List<Servico> servicos, List<Servico> atualizados) {
    for (Servico atualizado : atualizados) {
      for (Servico servico : servicos) {
        if (atualizado.getId() != null && atualizado.getId().equals(servico.getId())) {
          atualizar(servico, atualizado);
        }
      }
    }
  }
}
