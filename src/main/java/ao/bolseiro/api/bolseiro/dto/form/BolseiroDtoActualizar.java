package ao.bolseiro.api.bolseiro.dto.form;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.bolseiro.api.bolseiro.model.Bolseiro;
import ao.bolseiro.api.bolseiro.model.enumeration.Documento;
import ao.bolseiro.api.bolseiro.model.enumeration.Nivel_Academico;
import ao.bolseiro.api.bolseiro.repository.BolseiroRepository;
import lombok.Data;

@Data
public class BolseiroDtoActualizar {
	
	
	@Enumerated(EnumType.STRING)
	private Documento tipoDocBolseiro;
	private String codDocBolseiro;
	private int estudanteBolseiro;
	private LocalDate nascimentoBolseiro;
	private String nacionalidadeBolseiro;
	@Enumerated(EnumType.STRING)
	private Nivel_Academico nivelAcademicoBolseiro;
	
	
	
	
	public Bolseiro actualizar(Long id, BolseiroRepository bolseiroRepo) {
		Bolseiro bolseiro = bolseiroRepo.getOne(id);
		bolseiro.setTipoDocBolseiro(this.tipoDocBolseiro);
		bolseiro.setCodDocBolseiro(this.codDocBolseiro);
		bolseiro.setEstudanteBolseiro(this.estudanteBolseiro);
		bolseiro.setNascimentoBolseiro(this.nascimentoBolseiro);
		bolseiro.setNacionalidadeBolseiro(this.nacionalidadeBolseiro);
		bolseiro.setNivelAcademicoBolseiro(this.nivelAcademicoBolseiro);
		return bolseiro;
	}

}

