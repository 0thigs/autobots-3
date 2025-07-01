package com.autobots.automanager.entidades;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Endereco extends RepresentationModel<Endereco> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String logradouro;

  @Column(nullable = false)
  private String numero;

  @Column(nullable = false)
  private String cep;

  @Column
  private String complemento;
}
