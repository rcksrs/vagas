package br.ufma.vagas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufma.vagas.domain.geral.Usuario;
import br.ufma.vagas.service.geral.UsuarioService;
import br.ufma.vagas.service.perfil.PerfilService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {
	
	private UsuarioService usuarioService;
	private PerfilService perfilService;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> obterTodos(@PageableDefault(sort = "id", size = 20) Pageable pageable) {
		var usuarios = usuarioService.obterTodos(pageable);
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obterPorId(@PathVariable Long id) {
		var usuario = usuarioService.obterPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Usuario> obterPorCpf(@PathVariable String cpf) {
		var usuario = usuarioService.obterPorCpf(cpf);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> obterPorEmail(@PathVariable String email) {
		var usuario = usuarioService.obterPorEmail(email);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/perfil/resumo")
	public ResponseEntity<List<Usuario>> obterPorResumoPerfil(@RequestParam(required = true) String resumo) {
		var perfis = perfilService.obterPorResumo(resumo);
		var usuarios = perfis.stream().map(p -> p.getUsuario()).collect(Collectors.toList());
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		var usuarioSalvo = usuarioService.salvar(usuario);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(usuarioSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> editar(@RequestBody Usuario usuario) {
		var usuarioSalvo = usuarioService.editar(usuario);
		return ResponseEntity.ok(usuarioSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody Usuario usuario) {
		usuarioService.remover(usuario);
		return ResponseEntity.ok().build();
	}
	
	

}
