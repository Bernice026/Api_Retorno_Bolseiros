package ao.bolseiro.api.bolseiro.dto.cadastro;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.bolseiro.api.bolseiro.model.enumeration.Status_Avaliacao;
import lombok.Data;


@Data
public class AvaliacaoCadastroDto {
	
	
	private String nomeAvaliacao;
	private String cargoAvaliacao;
	private int desempenhoAvaliacao;
	private int interpessoalSkillAvaliacao;
	private int tecnicaSkillAvaliacao;
	private int objectivoAtingidoAvaliacao;
	@Enumerated(EnumType.STRING)
	private Status_Avaliacao statusAvaliacao;
	private String comentarioAvaliacao;

}
