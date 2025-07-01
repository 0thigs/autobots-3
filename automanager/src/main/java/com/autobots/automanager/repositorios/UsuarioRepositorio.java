package com.autobots.automanager.repositorios;

import org.springframework.stereotype.Repository;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByEndereco(Endereco endereco);
}
