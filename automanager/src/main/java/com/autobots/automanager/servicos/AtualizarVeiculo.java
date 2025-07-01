package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Veiculo;

@Component
public class AtualizarVeiculo {
  @Autowired
  private VerificarStringNula verificarString;

  public void atualizar(Veiculo veiculo, Veiculo atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getTipo().toString())) {
        veiculo.setTipo(atualizado.getTipo());
      }
      if (!verificarString.verificar(atualizado.getModelo())) {
        veiculo.setModelo(atualizado.getModelo());
      }
      if (!verificarString.verificar(atualizado.getPlaca())) {
        veiculo.setPlaca(atualizado.getPlaca());
      }
    }
  }

  public void atualizar(List<Veiculo> veiculos, List<Veiculo> atualizados) {
    for (Veiculo atualizado : atualizados) {
      for (Veiculo veiculo : veiculos) {
        if (atualizado.getId() != null && atualizado.getId().equals(veiculo.getId())) {
          atualizar(veiculo, atualizado);
        }
      }
    }
  }
}
