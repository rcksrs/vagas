package br.ufma.vagas.controller;

import java.util.List;

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

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.service.geral.AlunoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/aluno")
@AllArgsConstructor
public class AlunoController {
	
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<Page<Aluno>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var alunos = alunoService.obterTodos(pageable);
		return ResponseEntity.ok(alunos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> obterPorId(@PathVariable Long id) {
		var aluno = alunoService.obterPorId(id);
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<Aluno> obterPorMatricula(@PathVariable String matricula) {
		var aluno = alunoService.obterPorMatricula(matricula);
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Aluno>> obterPorNome(@PathVariable String nome) {
		var alunos = alunoService.obterPorNome(nome);
		return ResponseEntity.ok(alunos);
	}	
	
	@GetMapping("/curso/{cursoId}")
	public ResponseEntity<List<Aluno>> obterPorCurso(@PathVariable Long cursoId) {
		var alunos = alunoService.obterPorCurso(cursoId);
		return ResponseEntity.ok(alunos);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
		var alunoSalvo = alunoService.salvar(aluno);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(alunoSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(alunoSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Aluno> editar(@RequestBody Aluno aluno) {
		var alunoSalvo = alunoService.editar(aluno);
		return ResponseEntity.ok(alunoSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody Aluno aluno) {
		alunoService.remover(aluno);
		return ResponseEntity.ok().build();
	}

}
