package br.ufma.vagas.model;

import lombok.Data;

@Data
public class Idioma extends EntityBase {
	private String idioma;
	private Nivel nivel;

}
