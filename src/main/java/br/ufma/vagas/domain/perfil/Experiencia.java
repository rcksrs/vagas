package br.ufma.vagas.domain.perfil;

import java.time.LocalDate;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Experiencia extends EntityBase {
	private String titulo;
	private String descricao;
	private String urlComprovante;
	private String local;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private TipoExperiencia tipo;	

}
