package br.ufma.vagas.service.geral;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Aluno;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.DuplicatedResourceException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.AlunoRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class AlunoService extends ServiceBase<Aluno, AlunoRepository> {
	
	public Aluno obterPorMatricula(String matricula) {
		return repository.findByMatricula(matricula).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<Aluno> obterPorNome(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Aluno> obterPorCurso(Long cursoId) {
		return repository.findByCursoId(cursoId);
	}
	
	@Override
	public Aluno salvar(Aluno aluno) {
		var podeSalvar = aluno.getId() == null && repository.findByMatricula(aluno.getMatricula()).isEmpty();
		if(podeSalvar) return super.salvar(aluno);
		throw new DuplicatedResourceException();
	}
	
	@Override
	public Aluno editar(Aluno aluno) {
		var podeEditar = repository.findByIdAndMatricula(aluno.getId(), aluno.getMatricula()).isPresent();
		if(podeEditar) return super.editar(aluno);
		throw new BusinessException("Não é possível alterar a matrícula do aluno");		
	}

}
