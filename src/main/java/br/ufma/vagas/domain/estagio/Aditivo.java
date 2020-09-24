package br.ufma.vagas.domain.estagio;

import java.time.LocalDate;

import br.ufma.vagas.domain.EntityBase;
import lombok.Data;

@Data
public class Aditivo extends EntityBase {
	private String termo;
	private String observacao;
	private LocalDate dataInicio;
	private LocalDate dataFim;

}
