package ao.bolseiro.api.bolseiro.config.validacao;

import lombok.Data;

@Data
public class ErroDto {
	
	
	private String campo;
	private String erro;
	
	
	
	
	public ErroDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}


}
