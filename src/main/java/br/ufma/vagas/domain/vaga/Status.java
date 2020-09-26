package br.ufma.vagas.domain.vaga;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Status extends EntityBase implements Serializable {
	private static final long serialVersionUID = 3815889929792409957L;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoStatus tipo;

}
