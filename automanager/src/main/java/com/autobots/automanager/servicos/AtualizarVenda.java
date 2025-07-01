package com.autobots.automanager.servicos;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Venda;

@Component
public class AtualizarVenda {
  @Autowired
  private VerificarStringNula verificarString;

  public void atualizar(Venda venda, Venda atualizada) {
    if (atualizada != null) {
      if (!verificarString.verificar(atualizada.getIdentificacao())) {
        venda.setIdentificacao(atualizada.getIdentificacao());
      }
      if (atualizada.getDataCadastro() != null) {
        venda.setDataCadastro(atualizada.getDataCadastro());
      }
    }
  }

  public void atualizar(Set<Venda> vendas, Set<Venda> atualizadas) {
    for (Venda atualizada : atualizadas) {
      for (Venda venda : vendas) {
        if (atualizada.getId() != null && atualizada.getId().equals(venda.getId())) {
          atualizar(venda, atualizada);
        }
      }
    }
  }
}
