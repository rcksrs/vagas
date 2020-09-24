package br.ufma.vagas.domain.vaga;

import java.time.LocalDate;
import java.util.List;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Curso;
import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.domain.perfil.TipoExperiencia;
import lombok.Data;

@Data
public class Vaga extends EntityBase {
	private String titulo;
	private String desricao;
	private Integer vagas;
	private LocalDate dataLimite;
	private TipoExperiencia tipo;
	private Empresa empresa;
	private List<Curso> cursos;

}
