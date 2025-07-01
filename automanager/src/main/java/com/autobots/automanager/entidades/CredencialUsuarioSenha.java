package com.autobots.automanager.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CredencialUsuarioSenha extends Credencial {

  @Column(nullable = false, unique = true)
  private String nomeUsuario;

  @Column(nullable = false)
  private String senha;
}
