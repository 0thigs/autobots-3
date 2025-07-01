package com.autobots.automanager.entidades;

import java.util.Date;
import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Produto extends RepresentationModel<Produto> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date validade;

  @Column(nullable = false)
  private Date fabricacao;

  @Column(nullable = false)
  private Date dataCadastro;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private long quantidade;

  @Column(nullable = false)
  private double preco;

  @Column
  private String descricao;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "empresa_id", nullable = false)
  private Empresa empresa;
}
