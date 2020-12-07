package br.ufma.vagas.service.geral;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.repository.geral.CursoRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class CursoService extends ServiceBase<Curso, CursoRepository> {
	
	public List<Curso> obterPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}

}
