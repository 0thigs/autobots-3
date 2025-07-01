package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorios.EmpresaRepositorio;
import com.autobots.automanager.repositorios.UsuarioRepositorio;
import com.autobots.automanager.servicos.AdicionarLinkUsuario;
import com.autobots.automanager.servicos.AtualizarUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  @Autowired
  private EmpresaRepositorio empresaRepositorio;

  @Autowired
  private AdicionarLinkUsuario adicionadorLink;

  @Autowired
  private AtualizarUsuario atualizadorUsuario;

  @GetMapping("/listar")
  public ResponseEntity<List<Usuario>> listarUsuarios() {
    List<Usuario> usuarios = usuarioRepositorio.findAll();
    if (usuarios.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    adicionadorLink.adicionarLink(new HashSet<>(usuarios));
    return ResponseEntity.ok(usuarios);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id) {
    Optional<Usuario> usuario = usuarioRepositorio.findById(id);
    if (usuario.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    adicionadorLink.adicionarLink(usuario.get());
    return ResponseEntity.ok(usuario.get());
  }

  @PostMapping("/criar")
  public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
    if (usuario.getEmpresa() == null || usuario.getEmpresa().getId() == null) {
      return ResponseEntity.badRequest().body("Empresa é obrigatória");
    }

    Optional<Empresa> empresa = empresaRepositorio.findById(usuario.getEmpresa().getId());
    if (empresa.isEmpty()) {
      return ResponseEntity.badRequest().body("Empresa não encontrada");
    }

    usuario.setEmpresa(empresa.get());

    Usuario novoUsuario = usuarioRepositorio.save(usuario);
    adicionadorLink.adicionarLink(novoUsuario);
    return ResponseEntity.ok(novoUsuario);
  }

  @PutMapping("/atualizar/{id}")
  public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario dadosAtualizados) {
    Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
    if (usuarioOptional.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Usuario usuario = usuarioOptional.get();
    atualizadorUsuario.atualizar(usuario, dadosAtualizados);
    Usuario usuarioAtualizado = usuarioRepositorio.save(usuario);

    usuarioAtualizado.getEmpresa().getRazaoSocial();

    adicionadorLink.adicionarLink(usuarioAtualizado);
    return ResponseEntity.ok(usuarioAtualizado);
  }

  @DeleteMapping("/excluir/{id}")
  public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
    Optional<Usuario> usuario = usuarioRepositorio.findById(id);
    if (usuario.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    usuarioRepositorio.delete(usuario.get());
    return ResponseEntity.noContent().build();
  }
}
