package br.ufma.vagas.domain.perfil;

import java.time.LocalDate;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.TipoCurso;
import lombok.Data;

@Data
public class Formacao extends EntityBase {
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private TipoCurso tipo;

}
