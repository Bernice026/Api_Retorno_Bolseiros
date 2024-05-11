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
@Table(name = "TB_USER_PERFIL")
public class UsuarioPerfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean estado;

	@ManyToOne
	@JoinColumn(name = "usuario_Id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "perfil_Id")
	private Perfil perfil;

	public UsuarioPerfil() {
	}

	public UsuarioPerfil(Long id) {

		this.id = id;
	}

	public UsuarioPerfil(Usuario usuario, Perfil perfil) {
		this.usuario = usuario;
		this.perfil = perfil;
	}
}
