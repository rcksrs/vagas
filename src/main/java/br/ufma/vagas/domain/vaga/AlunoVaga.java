package br.ufma.vagas.domain.vaga;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Aluno;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AlunoVaga extends EntityBase {
	private Integer pontuacao;
	
	@OneToOne
	private Aluno aluno;
	
	@OneToOne
	private Vaga vaga;
	
	@ManyToOne
	private Status status;

}
