package ao.bolseiro.api.bolseiro.dto.cadastro;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.bolseiro.api.bolseiro.model.enumeration.Documento;
import ao.bolseiro.api.bolseiro.model.enumeration.Nivel_Academico;
import lombok.Data;

@Data
public class BolseiroCadastrarDto {
	
	
	@Enumerated(EnumType.STRING)
	private Documento tipoDocBolseiro;
	private String codDocBolseiro;
	private int estudanteBolseiro;
	private LocalDate nascimentoBolseiro;
	private String nacionalidadeBolseiro;
	@Enumerated(EnumType.STRING)
	private Nivel_Academico nivelAcademicoBolseiro;
	
	
	

}
