package ao.bolseiro.api.bolseiro.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Actividade;
import lombok.Data;

@Data
public class ActividadeDto {
	
	
	private Long idActividade;
	private String nomeActividade;
	private String tipoActividade;
	private String duracaoActividade;
	private LocalDate dataInicioActividade;
	private LocalDate dataFimActividade;
	private String descricaoActividade;
	
	
	
	public ActividadeDto(Actividade actividade) {
		this.idActividade = actividade.getIdActividade();
		this.nomeActividade = actividade.getNomeActividade();
		this.tipoActividade = actividade.getTipoActividade();
		this.duracaoActividade = actividade.getDuracaoActividade();
		this.dataInicioActividade = actividade.getDataInicioActividade();
		this.dataFimActividade = actividade.getDataFimActividade();
		this.descricaoActividade = actividade.getDescricaoActividade();
	}



	public static Page<ActividadeDto> converter(Page<Actividade> actividade) {
		return actividade.map(ActividadeDto::new);
	}
	
	
	

}
