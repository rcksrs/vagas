package br.ufma.vagas.domain.geral;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoCurso extends EntityBase {
	private String descricao;
}
