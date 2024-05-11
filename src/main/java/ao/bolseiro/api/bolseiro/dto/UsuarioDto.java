package ao.bolseiro.api.bolseiro.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDto {
	
	
	private Long idUser;
	private String nome;
	private String email;
	private String nomeUsuario;
    private LocalDate dataCriacao;
    private byte[] foto;
    private String instituicao;
    
    
	public UsuarioDto( Usuario usuario) {
		super();
		this.idUser = usuario.getIdUser();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.nomeUsuario = usuario.getNomeUsuario();
		this.dataCriacao = usuario.getDataCriacao();
		this.foto = usuario.getFoto();
		this.instituicao = usuario.getInstituicao().getNomeInstituicao();
	}


	public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {	
		return usuarios.map(UsuarioDto::new);
	}
    
    
    
    
    


}
