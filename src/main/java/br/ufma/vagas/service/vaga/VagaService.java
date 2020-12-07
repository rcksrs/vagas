package br.ufma.vagas.service.vaga;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.vaga.AlunoVaga;
import br.ufma.vagas.domain.vaga.AlunoVaga.AlunoVagaId;
import br.ufma.vagas.domain.vaga.Vaga;
import br.ufma.vagas.exception.BusinessException;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.geral.AlunoRepository;
import br.ufma.vagas.repository.vaga.AlunoVagaRepository;
import br.ufma.vagas.repository.vaga.VagaRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class VagaService extends ServiceBase<Vaga, VagaRepository> {
	
	@Autowired
	private AlunoVagaRepository alunoVagaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Vaga> filtrarPorData(LocalDate dataInicial, LocalDate dataFinal) {
		return repository.findByEncerramentoBetween(dataInicial, dataFinal);
	}
	
	public List<Vaga> obterPorTipoExperiencia(Long tipoExperienciaId) {
		return repository.findByTipoId(tipoExperienciaId);
	}
	
	public List<Vaga> obterPorEmpresa(Long empresaId) {
		return repository.findByEmpresaId(empresaId);
	}
	
	public AlunoVaga statusAlunoVaga(Long alunoId, Long vagaId) {
		return alunoVagaRepository.findByIdAlunoIdAndIdVagaId(alunoId, vagaId).orElseThrow(ResourceNotFoundException::new);
	}
	
	public List<AlunoVaga> obterInscricoesPorAluno(Long alunoId) {
		return alunoVagaRepository.findByIdAlunoId(alunoId);
	}
	
	public List<AlunoVaga> obterAlunosPorVaga(Long vagaId) {
		return alunoVagaRepository.findByIdVagaId(vagaId);
	}
	
	public AlunoVaga candidatar(Long alunoId, Long vagaId) {
		var alunoSalvo = alunoRepository.findById(alunoId).orElseThrow(() -> new ResourceNotFoundException("O aluno informado não foi encontrado na base de dados"));
		if(!alunoSalvo.getUsuario().getEmailConfirmado()) throw new BusinessException("Confirme o email cadastrado antes de realizar a inscrição");
		
		var vagaSalva = repository.findById(vagaId).orElseThrow(() -> new ResourceNotFoundException("A vaga informada não foi encontrada na base de dados"));
				
		var validarPeriodo = vagaSalva.getAbertura().isBefore(LocalDate.now().plusDays(1L)) && vagaSalva.getEncerramento().isAfter(LocalDate.now().minusDays(1L));
		if(!validarPeriodo) throw new BusinessException("O período de inscrição para esta vaga encerrou");
		
		var validarCurso = vagaSalva.getCursos().size() == 0 || vagaSalva.getCursos().stream().filter(c -> c.getId() == alunoSalvo.getCurso().getId()).count() != 0;
		if(!validarCurso) throw new BusinessException("O aluno não possui nenhum dos cursos requisitados pela vaga");
		
		var alunoVaga = new AlunoVaga();
		alunoVaga.setAtivo(true);
		alunoVaga.setCriadoEm(LocalDateTime.now());
		alunoVaga.setSelecionado(false);
		alunoVaga.setPontuacao(0);
		alunoVaga.setId(new AlunoVagaId(alunoSalvo, vagaSalva));
		var alunoVagaSalvo = alunoVagaRepository.save(alunoVaga);
		
		//TODO: enviar email de confirmação ao aluno
		return alunoVagaSalvo;
	}
	
	@Override
	public Page<Vaga> obterTodos(Pageable pageable) {
		return repository.findByEncerramentoAfter(LocalDate.now().minusDays(1L), pageable);
	}
	
	@Override
	public void remover(Vaga vaga) {
		var vagaSalva = repository.findById(vaga.getId()).orElseThrow(ResourceNotFoundException::new);
		if(alunoVagaRepository.findByIdVagaId(vagaSalva.getId()).size() > 0) throw new BusinessException("Não é possível remover uma vaga com candidatos");
		repository.deleteById(vagaSalva.getId());
	}

}
