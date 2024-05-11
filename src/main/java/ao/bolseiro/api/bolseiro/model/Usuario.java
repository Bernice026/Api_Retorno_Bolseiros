package ao.bolseiro.api.bolseiro.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	private String nome;
	
	@Column(length=100)
	private String email;
	
	@Column(length=50, name="NOME_USUARIO")
	private String nomeUsuario;
	
	private String senha;
	
	@Column(nullable = false)
    private boolean admin;
	
    @Column(nullable = false)
    private boolean estado;
    
    @Column(nullable = false)
    
    private boolean valido;

    private LocalDate dataCriacao;
   
    @Lob
	@Basic(fetch=FetchType.LAZY)
    private byte[] foto;
    
    @ManyToOne
    @JoinColumn(name= "instituicao_id")
    private Instituicao instituicao;
    
    private Integer loginFalhado;

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean isPresent() {
		return true;
	}

	public Usuario(Long idUser) {
		this.idUser = idUser;
	}
	
	public Usuario() {
	}
}
