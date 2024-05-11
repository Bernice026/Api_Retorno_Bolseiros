package ao.bolseiro.api.bolseiro.model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




import lombok.Data;

@Data
@Entity
@Table(name = "TB_ACTIVIDADE")
public class Actividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idActividade;
	private String nomeActividade;
	private String tipoActividade;
	private String duracaoActividade;
	private LocalDate dataInicioActividade;
	private LocalDate dataFimActividade;
	private String descricaoActividade;

	

	public Actividade() {
	}



	public Actividade(String nomeActividade, String tipoActividade, String duracaoActividade,
			LocalDate dataInicioActividade, LocalDate dataFimActividade, String descricaoActividade) {
		super();
		this.nomeActividade = nomeActividade;
		this.tipoActividade = tipoActividade;
		this.duracaoActividade = duracaoActividade;
		this.dataInicioActividade = dataInicioActividade;
		this.dataFimActividade = dataFimActividade;
		this.descricaoActividade = descricaoActividade;
	}
	
	


}
