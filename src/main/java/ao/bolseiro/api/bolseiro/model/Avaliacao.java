package ao.bolseiro.api.bolseiro.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ao.bolseiro.api.bolseiro.model.enumeration.Status_Avaliacao;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_AVALIACAO")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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


	public Avaliacao() {
		
	}


	
	public Avaliacao(String nomeAvaliacao, String cargoAvaliacao, int desempenhoAvaliacao,
			int interpessoalSkillAvaliacao, int tecnicaSkillAvaliacao, int objectivoAtingidoAvaliacao,
			Status_Avaliacao statusAvaliacao, String comentarioAvaliacao) {
		this.nomeAvaliacao = nomeAvaliacao;
		this.cargoAvaliacao = cargoAvaliacao;
		this.desempenhoAvaliacao = desempenhoAvaliacao;
		this.interpessoalSkillAvaliacao = interpessoalSkillAvaliacao;
		this.tecnicaSkillAvaliacao = tecnicaSkillAvaliacao;
		this.objectivoAtingidoAvaliacao = objectivoAtingidoAvaliacao;
		this.statusAvaliacao = statusAvaliacao;
		this.comentarioAvaliacao = comentarioAvaliacao;
	}

	
	
	
}