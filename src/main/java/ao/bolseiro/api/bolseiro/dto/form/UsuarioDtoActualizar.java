package ao.bolseiro.api.bolseiro.dto.form;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ao.bolseiro.api.bolseiro.model.Usuario;
import ao.bolseiro.api.bolseiro.repository.UsuarioRepository;
import lombok.Data;

@Data
public class UsuarioDtoActualizar {
	
	
	private String nome;
	private String email;
	private String nomeUsuario;
	private String senha;
	
    private boolean estado;
    private boolean valido;
    private boolean admin; 
    @SuppressWarnings("static-access")
	private LocalDate dataCriacao = getDataCriacao().now();
    
    
    
	public Usuario actualizar(Long id, UsuarioRepository usuarioRepo) {
		Usuario usuario = usuarioRepo.getOne(id);
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setNomeUsuario(this.nomeUsuario);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		usuario.setEstado(this.estado);
		usuario.setValido(this.estado);
		usuario.setAdmin(this.admin);
		usuario.setDataCriacao(this.dataCriacao);
		return usuario;
	}

}
