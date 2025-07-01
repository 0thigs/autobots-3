package com.autobots.automanager.servicos;

import org.springframework.stereotype.Service;

@Service
public class VerificarStringNula {
  public boolean verificar(String dado) {
    boolean nulo = true;
    if (dado != null && !dado.isBlank()) {
      nulo = false;
    }
    return nulo;
  }
}
