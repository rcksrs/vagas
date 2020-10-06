package br.ufma.vagas.repository.vaga;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.vagas.domain.vaga.AlunoVaga;

public interface AlunoVagaRepository extends JpaRepository<AlunoVaga, AlunoVaga.AlunoVagaId> {
	Optional<AlunoVaga> findByIdAlunoIdAndIdVagaId(Long alunoId, Long vagaId);
	List<AlunoVaga> findByIdAlunoId(Long alunoId);
	List<AlunoVaga> findByIdVagaId(Long vagaId);

}
