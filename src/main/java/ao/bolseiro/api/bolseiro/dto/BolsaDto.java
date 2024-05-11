package ao.bolseiro.api.bolseiro.dto;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Bolsa;
import lombok.Data;

@Data
public class BolsaDto {
	
	
	private Long idBolsa;
	private String nomeBolsa;
	private String categoriaBolsa;
	private String tipoBolsa;
	private String duracaoBolsa;
	private String descricaoBolsa;
	
	
	public BolsaDto(Bolsa bolsa) {
		
		this.idBolsa = bolsa.getIdBolsa();
		this.nomeBolsa = bolsa.getNomeBolsa();
		this.categoriaBolsa = bolsa.getCategoriaBolsa();
		this.tipoBolsa = bolsa.getTipoBolsa();
		this.duracaoBolsa = bolsa.getDuracaoBolsa();
		this.descricaoBolsa = bolsa.getDescricaoBolsa();
	}


	public static Page<BolsaDto> converter(Page<Bolsa> bolsa) {
		return bolsa.map(BolsaDto::new);
	}
	
	
	

}
