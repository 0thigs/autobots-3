package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entidades.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
}
