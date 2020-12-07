package br.ufma.vagas.service.perfil;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.perfil.Perfil;
import br.ufma.vagas.repository.perfil.PerfilRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class PerfilService extends ServiceBase<Perfil, PerfilRepository> {

	public List<Perfil> obterPorResumo(String resumo) {
		return repository.findByResumoContainingIgnoreCase(resumo);
	}
}
