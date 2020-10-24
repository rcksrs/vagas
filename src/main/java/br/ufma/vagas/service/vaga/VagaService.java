package br.ufma.vagas.service.vaga;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.vaga.AlunoVaga;
import br.ufma.vagas.domain.vaga.Vaga;
import br.ufma.vagas.exception.ResourceNotFoundException;
import br.ufma.vagas.repository.vaga.AlunoVagaRepository;
import br.ufma.vagas.repository.vaga.VagaRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class VagaService extends ServiceBase<Vaga, VagaRepository> {
		
	@Autowired
	private AlunoVagaRepository alunoVagaRepository;
	
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

}
