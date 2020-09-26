package br.ufma.vagas.domain.perfil;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.ufma.vagas.domain.EntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Idioma extends EntityBase {
	private String idioma;
	
	@Enumerated(EnumType.STRING)
	private Nivel nivel;

}
