package br.ufma.vagas.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.service.geral.CursoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/curso")
@AllArgsConstructor
public class CursoController {
	
	private CursoService cursoService;
	
	@GetMapping
	public ResponseEntity<Page<Curso>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var cursos = cursoService.obterTodos(pageable);
		return ResponseEntity.ok(cursos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Curso> obterPorId(@PathVariable Long id) {
		var curso = cursoService.obterPorId(id);
		return ResponseEntity.ok(curso);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Curso>> obterPorNome(@PathVariable String nome) {
		var cursos = cursoService.obterPorNome(nome);
		return ResponseEntity.ok(cursos);
	}
	
	@PostMapping
	public ResponseEntity<Curso> salvar(@RequestBody @Valid Curso curso) {
		var cursoSalvo = cursoService.salvar(curso);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cursoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(cursoSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Curso> editar(@RequestBody @Valid Curso curso) {
		var cursoSalvo = cursoService.editar(curso);
		return ResponseEntity.ok(cursoSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody @Valid Curso curso) {
		cursoService.remover(curso);
		return ResponseEntity.ok().build();
	}

}
