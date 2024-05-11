package ao.bolseiro.api.bolseiro.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PERFIL_FUNCIONALIDADE")
public class FuncionalidadePerfil {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="funcionalidade_id")
	private Funcionalidade funcionalidade;
	
	@ManyToOne
	@JoinColumn(name="perfil_id")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
}
