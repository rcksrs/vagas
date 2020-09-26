package br.ufma.vagas.domain.perfil;

import java.io.Serializable;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoExperiencia extends EntityBase implements Serializable {
	private static final long serialVersionUID = -2379195629662993941L;
	
	private String descricao;
	
}
