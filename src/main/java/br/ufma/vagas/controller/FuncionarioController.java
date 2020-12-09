package br.ufma.vagas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufma.vagas.domain.geral.Funcionario;
import br.ufma.vagas.service.geral.FuncionarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/funcionario")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class FuncionarioController {
	
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public ResponseEntity<Page<Funcionario>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var funcionarios = funcionarioService.obterTodos(pageable);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Funcionario>> obterTodos() {
		var funcionarios = funcionarioService.obterTodos(Sort.by("nome"));
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> obterPorId(@PathVariable Long id) {
		var funcionario = funcionarioService.obterPorId(id);
		return ResponseEntity.ok(funcionario);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Funcionario>> obterPorNome(@PathVariable String nome) {
		var funcionarios = funcionarioService.obterPorNome(nome);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/matricula/{matricula}")
	public ResponseEntity<Funcionario> obterPorMatricula(@PathVariable String matricula) {
		var funcionario = funcionarioService.obterPorMatricula(matricula);
		return ResponseEntity.ok(funcionario);
	}
	
	@GetMapping("/empresa/{empresaId}")
	public ResponseEntity<List<Funcionario>> obterPorEmpresa(@PathVariable Long empresaId) {
		var funcionarios = funcionarioService.obterPorEmpresa(empresaId);
		return ResponseEntity.ok(funcionarios);
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) {
		var funcionarioSalvo = funcionarioService.salvar(funcionario);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(funcionarioSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(funcionarioSalvo);
	}
	
	@PutMapping
	public ResponseEntity<Funcionario> editar(@RequestBody Funcionario funcionario) {
		var funcionarioSalvo = funcionarioService.editar(funcionario);
		return ResponseEntity.ok(funcionarioSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody Funcionario funcionario) {
		funcionarioService.remover(funcionario);
		return ResponseEntity.ok().build();
	}

}
