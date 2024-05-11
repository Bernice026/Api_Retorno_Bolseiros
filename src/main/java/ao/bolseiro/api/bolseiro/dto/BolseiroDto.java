package ao.bolseiro.api.bolseiro.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Bolseiro;
import ao.bolseiro.api.bolseiro.model.enumeration.Documento;
import ao.bolseiro.api.bolseiro.model.enumeration.Nivel_Academico;
import lombok.Data;

@Data
public class BolseiroDto {
	
	
	private Long idBolseiro;
	@Enumerated(EnumType.STRING)
	private Documento tipoDocBolseiro;
	@Column(unique = true)
	private String codDocBolseiro;
	private int estudanteBolseiro;
	private LocalDate nascimentoBolseiro;
	private String nacionalidadeBolseiro;
	@Enumerated(EnumType.STRING)
	private Nivel_Academico nivelAcademicoBolseiro;

	
	private String usuario;
	private String bolsa;
	private String curso;
	
	
	
	public BolseiroDto(Bolseiro bolseiro) {
		
		this.idBolseiro = bolseiro.getIdBolseiro();
		this.tipoDocBolseiro = bolseiro.getTipoDocBolseiro();
		this.codDocBolseiro = bolseiro.getCodDocBolseiro();
		this.estudanteBolseiro = bolseiro.getEstudanteBolseiro();
		this.nascimentoBolseiro = bolseiro.getNascimentoBolseiro();
		this.nacionalidadeBolseiro = bolseiro.getNacionalidadeBolseiro();
		this.nivelAcademicoBolseiro = bolseiro.getNivelAcademicoBolseiro();
	}



	public static Page<BolseiroDto> converter(Page<Bolseiro> bolseiro) {
		return bolseiro.map(BolseiroDto::new);
	}
	

}

