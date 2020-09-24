package br.ufma.vagas.domain.perfil;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Idioma extends EntityBase {
	private String idioma;
	private Nivel nivel;

}
