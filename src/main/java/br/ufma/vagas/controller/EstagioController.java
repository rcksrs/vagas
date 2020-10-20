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

import br.ufma.vagas.domain.estagio.VinculoEstagio;
import br.ufma.vagas.service.estagio.EstagioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/estagio")
@AllArgsConstructor
public class EstagioController {
	
	private EstagioService estagioService;
	
	@GetMapping
	public ResponseEntity<Page<VinculoEstagio>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var estagios = estagioService.obterTodos(pageable);
		return ResponseEntity.ok(estagios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VinculoEstagio> obterPorId(Long id) {
		var estagio = estagioService.obterPorId(id);
		return ResponseEntity.ok(estagio);
	}
	
	@GetMapping("/aluno/{alunoId}")
	public ResponseEntity<List<VinculoEstagio>> obterPorAluno(@PathVariable Long alunoId) {
		var estagios = estagioService.obterPorAluno(alunoId);
		return ResponseEntity.ok(estagios);
	}
	
	@GetMapping("/empresa/{empresaId}")
	public ResponseEntity<List<VinculoEstagio>> obterPorEmpresa(@PathVariable Long empresaId) {
		var estagios = estagioService.obterPorEmpresa(empresaId);
		return ResponseEntity.ok(estagios);
	}
	
	@PostMapping
	public ResponseEntity<VinculoEstagio> salvar(@RequestBody VinculoEstagio estagio) {
		//TODO: verificar se o aluno e a empresa informados existem
		var estagioSalvo = estagioService.salvar(estagio);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(estagioSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(estagioSalvo);
	}
	
	@PutMapping
	public ResponseEntity<VinculoEstagio> editar(@RequestBody VinculoEstagio estagio) {
		var estagioSalvo = estagioService.editar(estagio);
		return ResponseEntity.ok(estagioSalvo);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody VinculoEstagio estagio) {
		estagioService.remover(estagio);
		return ResponseEntity.ok().build();
	}

}
