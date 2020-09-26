package br.ufma.vagas.domain.vaga;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Status extends EntityBase {
	private String descricao;

}
