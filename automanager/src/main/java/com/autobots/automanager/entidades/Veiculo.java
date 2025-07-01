package com.autobots.automanager.entidades;

import java.util.*;
import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import com.autobots.automanager.enumeracoes.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "proprietario", "vendas" }, callSuper = false)
public class Veiculo extends RepresentationModel<Veiculo> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private TipoVeiculo tipo;

  @Column(nullable = false)
  private String modelo;

  @Column(nullable = false)
  private String placa;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "empresa_id", nullable = false)
  private Empresa empresa;

  @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JsonIgnore
  private Usuario proprietario;

  @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JsonIgnore
  private Set<Venda> vendas = new HashSet<>();
}
