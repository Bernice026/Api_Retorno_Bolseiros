package ao.bolseiro.api.bolseiro.model;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import ao.bolseiro.api.bolseiro.model.enumeration.Documento;
import ao.bolseiro.api.bolseiro.model.enumeration.Nivel_Academico;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_BOLSEIRO")
public class Bolseiro {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	

	

	public Bolseiro() {

	}
	
	
	public Bolseiro(Documento tipoDocBolseiro, String codDocBolseiro, int estudanteBolseiro,
			LocalDate nascimentoBolseiro, String nacionalidadeBolseiro, Nivel_Academico nivelAcademicoBolseiro) {
		this.tipoDocBolseiro = tipoDocBolseiro;
		this.codDocBolseiro = codDocBolseiro;
		this.estudanteBolseiro = estudanteBolseiro;
		this.nascimentoBolseiro = nascimentoBolseiro;
		this.nacionalidadeBolseiro = nacionalidadeBolseiro;
		this.nivelAcademicoBolseiro = nivelAcademicoBolseiro;
	}
	
	
}

