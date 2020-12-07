package br.ufma.vagas.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.domain.perfil.TipoExperiencia;
import br.ufma.vagas.domain.vaga.AlunoVaga;
import br.ufma.vagas.domain.vaga.Vaga;
import br.ufma.vagas.service.vaga.VagaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vaga")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class VagaController {
	
	private VagaService vagaService;
	
	@GetMapping
	public ResponseEntity<Page<Vaga>> obterTodos(@PageableDefault(sort = "abertura", size = 10) Pageable pageable) {
		var vagas = vagaService.obterTodos(pageable);
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Vaga>> obterTodos() {
		var vagas = vagaService.obterTodos(Sort.by("criadoEm", "encerramento").descending());
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vaga> obterPorId(@PathVariable Long id) {
		var vaga = vagaService.obterPorId(id);
		return ResponseEntity.ok(vaga);
	}
	
	@GetMapping("/data/{dataInicial}/{dataFinal}")
	public ResponseEntity<List<Vaga>> filtrarPorData(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal) {
		var vagas = vagaService.filtrarPorData(dataInicial, dataFinal);
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/tipo")
	public ResponseEntity<List<TipoExperiencia>> obterTiposExperiencia() {
		var tipos = vagaService.obterTiposExepriencia();
		return ResponseEntity.ok(tipos);
	}
	
	@GetMapping("/tipo/{tipoExperienciaId}")
	public ResponseEntity<List<Vaga>> obterPorTipoExperiencia(@PathVariable Long tipoExperienciaId) {
		var vagas = vagaService.obterPorTipoExperiencia(tipoExperienciaId);
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/empresa/{empresaId}")
	public ResponseEntity<List<Vaga>> obterPorEmpresa(@PathVariable Long empresaId) {
		var vagas = vagaService.obterPorEmpresa(empresaId);
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/status/{alunoId}/{vagaId}")
	public ResponseEntity<AlunoVaga> statusAlunoVaga(@PathVariable Long alunoId, @PathVariable Long vagaId) {
		var vaga = vagaService.statusAlunoVaga(alunoId, vagaId);
		return ResponseEntity.ok(vaga);
	}
	
	@GetMapping("/inscricoes/{alunoId}")
	public ResponseEntity<List<AlunoVaga>> obterInscricoesPorAluno(@PathVariable Long alunoId) {
		var vagas = vagaService.obterInscricoesPorAluno(alunoId);
		return ResponseEntity.ok(vagas);
	}
	
	@GetMapping("/status/{vagaId}")
	public ResponseEntity<List<AlunoVaga>> obterAlunosPorVaga(@PathVariable Long vagaId) {
		var vagas = vagaService.obterAlunosPorVaga(vagaId);
		return ResponseEntity.ok(vagas);
	}
	
	@PostMapping
	public ResponseEntity<Vaga> salvar(@RequestBody @Valid Vaga vaga) {
		var vagaSalva = vagaService.salvar(vaga);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(vagaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(vagaSalva);
	}
	
	@PostMapping("/candidatar/{vagaId}")
	public ResponseEntity<AlunoVaga> candidatar(@RequestBody @Valid Aluno aluno, @PathVariable Long vagaId) {
		var vaga = vagaService.candidatar(aluno.getId(), vagaId);
		return ResponseEntity.ok(vaga);
	}
	
	@PostMapping("/classificar")
	public ResponseEntity<AlunoVaga> classificar(@RequestBody @Valid AlunoVaga alunoVaga) {
		var alunoVagaSalvo = vagaService.classificar(alunoVaga);
		return ResponseEntity.ok(alunoVagaSalvo);
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody Vaga vaga) {
		vagaService.remover(vaga);
		return ResponseEntity.ok().build();
	}

}
