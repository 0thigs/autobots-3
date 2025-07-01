package com.autobots.automanager.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Endereco;

@Service
public class AtualizarEndereco {
  @Autowired
  private VerificarStringNula verificarString;

  public void atualizar(Endereco original, Endereco atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getEstado())) {
        original.setEstado(atualizado.getEstado());
      }
      if (!verificarString.verificar(atualizado.getCidade())) {
        original.setCidade(atualizado.getCidade());
      }
      if (!verificarString.verificar(atualizado.getBairro())) {
        original.setBairro(atualizado.getBairro());
      }
      if (!verificarString.verificar(atualizado.getLogradouro())) {
        original.setLogradouro(atualizado.getLogradouro());
      }
      if (!verificarString.verificar(atualizado.getNumero())) {
        original.setNumero(atualizado.getNumero());
      }
      if (!verificarString.verificar(atualizado.getCep())) {
        original.setCep(atualizado.getCep());
      }
      if (!verificarString.verificar(atualizado.getComplemento())) {
        original.setComplemento(atualizado.getComplemento());
      }
    }
  }
}
