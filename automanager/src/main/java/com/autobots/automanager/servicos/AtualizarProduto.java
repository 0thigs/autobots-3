package com.autobots.automanager.servicos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Produto;

@Service
public class AtualizarProduto {
  @Autowired
  private VerificarStringNula verificarString;
  @Autowired
  private VerificarDoubleNulo verificarDouble;

  public void atualizar(Produto produto, Produto atualizado) {
    if (atualizado != null) {
      if (!verificarString.verificar(atualizado.getNome())) {
        produto.setNome(atualizado.getNome());
      }
      if (!verificarDouble.verificar(atualizado.getPreco())) {
        produto.setPreco(atualizado.getPreco());
      }
      if (atualizado.getDataCadastro() != null) {
        produto.setDataCadastro(atualizado.getDataCadastro());
      }
      if (atualizado.getFabricacao() != null) {
        produto.setFabricacao(atualizado.getFabricacao());
      }
      if (atualizado.getValidade() != null) {
        produto.setValidade(atualizado.getValidade());
      }
      if (atualizado.getQuantidade() >= 0) {
        produto.setQuantidade(atualizado.getQuantidade());
      }
      if (!verificarString.verificar(atualizado.getDescricao())) {
        produto.setDescricao(atualizado.getDescricao());
      }
    }
  }

  public void atualizar(List<Produto> produtos, List<Produto> atualizados) {
    for (Produto atualizado : atualizados) {
      for (Produto produto : produtos) {
        if (atualizado.getId() != null && atualizado.getId().equals(produto.getId())) {
          atualizar(produto, atualizado);
        }
      }
    }
  }
}
