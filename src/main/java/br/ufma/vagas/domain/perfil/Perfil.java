package br.ufma.vagas.domain.perfil;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufma.vagas.domain.EntityBase;
import br.ufma.vagas.domain.geral.Usuario;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Perfil extends EntityBase implements Serializable {
	private static final long serialVersionUID = 4923260792812529690L;
	
	private String resumo;
	private String urlLattes;
	private String urlImagem;
	
	@JsonIgnore
	@OneToOne(mappedBy = "perfil")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Experiencia> experiencias;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Formacao> formacoes;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id")
	private List<Idioma> idiomas;

}
