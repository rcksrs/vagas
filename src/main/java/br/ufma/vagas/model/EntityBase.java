package br.ufma.vagas.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class EntityBase {
	protected Long id;
	protected LocalDateTime createdAt;
	protected LocalDateTime updatedAt;
	protected Boolean isActive;

}
