package br.ufma.vagas.model;

import lombok.Data;

@Data
public class Endereco {
	private String cep;
	private String pais;
	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String referencia;

}
