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

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.service.geral.EmpresaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/empresa")
@AllArgsConstructor
public class EmpresaController {
	
	private EmpresaService empresaService;
	
	@GetMapping
	public ResponseEntity<Page<Empresa>> obterTodos(@PageableDefault(sort = "nome", size = 20) Pageable pageable) {
		var empresas = empresaService.obterTodos(pageable);
		return ResponseEntity.ok(empresas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> obterPorId(@PathVariable Long id) {
		var empresa = empresaService.obterPorId(id);
		return ResponseEntity.ok(empresa);
	}
	
	@GetMapping("/cnpj/{cnpj}")
	public ResponseEntity<Empresa> obterPorCnpj(@PathVariable String cnpj) {
		var empresa = empresaService.obterPorCnpj(cnpj);
		return ResponseEntity.ok(empresa);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Empresa>> obterPorNome(@PathVariable String nome) {
		var empresas = empresaService.obterPorNome(nome);
		return ResponseEntity.ok(empresas);
	}
	
	@PostMapping
	public ResponseEntity<Empresa> salvar(@RequestBody @Valid Empresa empresa) {
		var empresaSalva = empresaService.salvar(empresa);
		var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(empresaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(empresaSalva);
	}
	
	@PutMapping
	public ResponseEntity<Empresa> editar(@RequestBody @Valid Empresa empresa) {
		var empresaSalva = empresaService.editar(empresa);
		return ResponseEntity.ok(empresaSalva);	
	}
	
	@DeleteMapping
	public ResponseEntity<?> remover(@RequestBody @Valid Empresa empresa) {
		empresaService.remover(empresa);
		return ResponseEntity.ok().build();
	}

}
