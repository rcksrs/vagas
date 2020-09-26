package br.ufma.vagas.domain.perfil;

import javax.persistence.Entity;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TipoExperiencia extends EntityBase {
	private String descricao;
	
}
