package ao.bolseiro.api.bolseiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_FUNCIONALIDADE")
public class Funcionalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	private String descricao;
	
	@Column(name="estado", nullable=true)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;
}
