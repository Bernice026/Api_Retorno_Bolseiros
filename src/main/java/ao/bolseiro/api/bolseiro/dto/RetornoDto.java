package ao.bolseiro.api.bolseiro.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Retorno;
import ao.bolseiro.api.bolseiro.model.enumeration.Status;
import lombok.Data;

@Data
public class RetornoDto {
	
	
	private Long idRetorno;
	private String tipoRetorno;
	private String categoriaRetorno;
	private String duracaoRetorno;
	private LocalDate dataInicioRetorno;
	private LocalDate dataFimRetorno;
	@Enumerated(EnumType.STRING)
	private Status statusRetorno
	
	;
	public RetornoDto(Retorno retorno) {
		super();
		this.idRetorno = retorno.getIdRetorno();
		this.tipoRetorno = retorno.getTipoRetorno();
		this.categoriaRetorno =retorno.getCategoriaRetorno();
		this.duracaoRetorno = retorno.getDuracaoRetorno();
		this.dataInicioRetorno = retorno.getDataInicioRetorno();
		this.dataFimRetorno = retorno.getDataFimRetorno();
		this.statusRetorno = retorno.getStatusRetorno();
	}
	
	
	public static Page<RetornoDto> converter(Page<Retorno> retorno) {
		return retorno.map(RetornoDto::new);
	}
	
	
	

}
