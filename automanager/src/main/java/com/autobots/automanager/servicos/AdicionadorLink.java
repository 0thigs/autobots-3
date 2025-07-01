package com.autobots.automanager.servicos;

import java.util.Set;

public interface AdicionadorLink<T> {
  public void adicionarLink(Set<T> lista);
  public void adicionarLink(T objeto);
}
