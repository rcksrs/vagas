package br.ufma.vagas.service.estagio;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.estagio.VinculoEstagio;
import br.ufma.vagas.repository.estagio.VinculoEstagioRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class EstagioService extends ServiceBase<VinculoEstagio, VinculoEstagioRepository> {

	public List<VinculoEstagio> obterPorAluno(Long alunoId) {
		return repository.findByAlunoId(alunoId);
	}
	
	public List<VinculoEstagio> obterPorEmpresa(Long empresaId) {
		return repository.findByEmpresaId(empresaId);
	}
}
