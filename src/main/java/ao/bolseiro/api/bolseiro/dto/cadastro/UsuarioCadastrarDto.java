package ao.bolseiro.api.bolseiro.dto.cadastro;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioCadastrarDto {

	
	private String nome;
	
	private String email;
	
	private String nomeUsuario;
	
	private String senha;
    
	private Long codigoInstituicao;
   
    private boolean estado;
    
    private boolean valido;
    
    private boolean admin;
    
    private LocalDate dataCriacao;
}
