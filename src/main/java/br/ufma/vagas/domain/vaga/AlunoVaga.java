package br.ufma.vagas.domain.vaga;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufma.vagas.domain.geral.Aluno;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AlunoVaga implements Serializable {
	private static final long serialVersionUID = 635348355258598348L;
	
	private LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;
	private Boolean ativo;
	private Integer pontuacao;
	
	@EmbeddedId
	private AlunoVagaId id;
	
	@ManyToOne
	private Status status;

}

@Embeddable
@Getter @Setter
class AlunoVagaId {
	
	@OneToOne
	private Aluno aluno;
	
	@OneToOne
	private Vaga vaga;
}
