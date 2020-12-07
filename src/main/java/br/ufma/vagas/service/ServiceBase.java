package br.ufma.vagas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.RepositoryBase;

public abstract class ServiceBase<E extends EntityBase, R extends RepositoryBase<E>> {
	
	@Autowired
	protected R repository;
	
	public E obterPorId(Long id) {
		return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<E> obterTodos() {
		return repository.findByAtivoTrue();
	}
	
	public List<E> obterTodos(Sort sort) {
		return repository.findByAtivoTrue(sort);
	}
	
	public List<E> obterTodos(Example<E> example) {
		return repository.findAll(example);
	}
	
	public Page<E> obterTodos(Pageable pageable) {
		return repository.findByAtivoTrue(pageable);
	}
	
	public E salvar(E entity) {
//		entity.setAtivo(true);
//		entity.setCriadoEm(LocalDateTime.now());
		return repository.save(entity);
	}
	
	public E editar(E entity) {
		var entitySaved = repository.findById(entity.getId()).orElseThrow(ResourceNotFoundException::new);
		entity.setAtualizadoEm(LocalDateTime.now());
		BeanUtils.copyProperties(entity, entitySaved, "id", "ativo", "criadoEm");
		return repository.save(entitySaved);
	}
	
	public void remover(E entity) {
		var e = repository.findById(entity.getId()).orElseThrow(ResourceNotFoundException::new);
		repository.deleteById(e.getId());
	}

}
