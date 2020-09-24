package br.ufma.vagas.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VinculoEstagio extends EntityBase {
	private Aluno aluno;
	private Empresa empresa;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private List<Aditivo> aditivos;

}
