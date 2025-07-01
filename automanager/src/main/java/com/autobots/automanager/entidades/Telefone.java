package com.autobots.automanager.entidades;

import javax.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Telefone extends RepresentationModel<Telefone> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String ddd;

  @Column(nullable = false)
  private String numero;

  @Column(name = "cliente_id")
  @JsonIgnore
  private Long clienteId;
}
