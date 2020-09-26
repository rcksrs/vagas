package br.ufma.vagas.domain.geral;

import java.io.Serializable;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoCurso extends EntityBase implements Serializable {
	private static final long serialVersionUID = 4187046827747342034L;
	
	private String descricao;
}
