package ao.bolseiro.api.bolseiro.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Avaliacao;
import ao.bolseiro.api.bolseiro.model.enumeration.Status_Avaliacao;
import lombok.Data;

@Data
public class AvaliacaoDto {
	
	private Long idAvaliacao;
	private String nomeAvaliacao;
	private String cargoAvaliacao;
	private int desempenhoAvaliacao;
	private int interpessoalSkillAvaliacao;
	private int tecnicaSkillAvaliacao;
	private int objectivoAtingidoAvaliacao;
	@Enumerated(EnumType.STRING)
	private Status_Avaliacao statusAvaliacao;
	private String comentarioAvaliacao;
	
	
	
	public AvaliacaoDto(Avaliacao avaliacao) {
		super();
		this.idAvaliacao = avaliacao.getIdAvaliacao();
		this.nomeAvaliacao = avaliacao.getNomeAvaliacao();
		this.cargoAvaliacao = avaliacao.getCargoAvaliacao();
		this.desempenhoAvaliacao = avaliacao.getDesempenhoAvaliacao();
		this.interpessoalSkillAvaliacao = avaliacao.getInterpessoalSkillAvaliacao();
		this.tecnicaSkillAvaliacao = avaliacao.getTecnicaSkillAvaliacao();
		this.objectivoAtingidoAvaliacao = avaliacao.getObjectivoAtingidoAvaliacao();
		this.statusAvaliacao = avaliacao.getStatusAvaliacao();
		this.comentarioAvaliacao = avaliacao.getComentarioAvaliacao();
	}



	public static Page<AvaliacaoDto> converter(Page<Avaliacao> avaliacao) {
		return avaliacao.map(AvaliacaoDto::new);
	}
	
	

}
