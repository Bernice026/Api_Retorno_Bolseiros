package ao.bolseiro.api.bolseiro.dto;

import java.util.List;

import lombok.Data;

@Data
public class LoginRetornoDTO {

	private boolean admin;
	
	private Long usuarioId;
	private String nome;
	private String nomeUsuario;
	private String email;
	
	private InstituicaoRetorno instituicao;
	private List<PerfilFuncionalidadeDTO2> perfil;
}
