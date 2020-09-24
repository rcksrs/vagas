package br.ufma.vagas.model;

import java.util.List;

import lombok.Data;

@Data
public class Empresa extends EntityBase {
	private String nome;
	private String cnpj;
	private String email;
	private String site;
	private Endereco endereco;
	private List<Contato> contatos;
	private List<Funcionario> funcionarios;

}
