package br.ufma.vagas.domain.perfil;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Idioma extends EntityBase implements Serializable {
	private static final long serialVersionUID = 5971763341312969725L;

	private String idioma;
	
	@Enumerated(EnumType.STRING)
	private Nivel nivel;

}
