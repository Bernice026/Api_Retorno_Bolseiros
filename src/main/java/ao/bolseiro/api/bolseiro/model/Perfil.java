package ao.bolseiro.api.bolseiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PERFIL")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(nullable = false)
	private boolean estado;
	
	
	

	public Perfil() {
	}

	public Perfil(Long id) {
		this.id = id;
	}
}
