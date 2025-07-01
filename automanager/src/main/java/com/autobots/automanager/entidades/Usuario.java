package com.autobots.automanager.entidades;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import com.autobots.automanager.enumeracoes.PapelUsuario;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends RepresentationModel<Usuario> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @Column
  private String nomeSocial;

  @Enumerated(EnumType.STRING)
  private PapelUsuario papel;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "empresa_id", nullable = false)
  private Empresa empresa;

  @OneToOne(optional = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  @Column(nullable = false)
  private Boolean inativo;

  @Column
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate ultimoAcesso = LocalDate.now();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "usuario_id")
  private Set<Documento> documentos = new HashSet<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "usuario_id")
  private Set<Telefone> telefones = new HashSet<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "usuario_id")
  private Set<Email> emails = new HashSet<>();

  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private CredencialUsuarioSenha credencialUsuarioSenha;

  @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private CredencialCodigoBarras credencialCodigoBarras;

  @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JoinColumn(name = "usuario_id")
  private Set<Veiculo> veiculos = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
  @JoinColumn(name = "usuario_id")
  @JsonIgnore
  private Set<Venda> vendas = new HashSet<>();
}
