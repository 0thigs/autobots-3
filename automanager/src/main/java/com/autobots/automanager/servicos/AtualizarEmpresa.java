package com.autobots.automanager.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Empresa;

@Service
public class AtualizarEmpresa {
  @Autowired
  private VerificarStringNula verificarString;
  @Autowired
  private AtualizarEndereco atualizarEndereco;
  @Autowired
  private AtualizarTelefone atualizarTelefone;

  public void atualizar(Empresa empresa, Empresa atualizada) {
    atualizarCampos(empresa, atualizada);
    atualizarEndereco.atualizar(empresa.getEndereco(), atualizada.getEndereco());
    atualizarTelefone.atualizar(empresa.getTelefones(), atualizada.getTelefones());
  }

  private void atualizarCampos(Empresa empresa, Empresa atualizada) {
    if (atualizada != null) {
      if (!verificarString.verificar(atualizada.getRazaoSocial())) {
        empresa.setRazaoSocial(atualizada.getRazaoSocial());
      }
      if (!verificarString.verificar(atualizada.getNomeFantasia())) {
        empresa.setNomeFantasia(atualizada.getNomeFantasia());
      }
    }
  }
}
