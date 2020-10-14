package br.ufma.vagas.exception;

public class DuplicatedResourceException extends RuntimeException {
	private static final long serialVersionUID = 5266023531583283367L;

	public DuplicatedResourceException() {
		super("Erro ao salvar informações, o recurso já existe");
	}
	
	public DuplicatedResourceException(String message) {
		super(message);
	}
}
