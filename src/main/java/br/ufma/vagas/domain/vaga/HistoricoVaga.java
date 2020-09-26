package br.ufma.vagas.domain.vaga;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class HistoricoVaga extends EntityBase {
	private String descricao;
	
	@ManyToOne
	private Vaga vaga;
	
	@ManyToOne
	private Status status;

}
