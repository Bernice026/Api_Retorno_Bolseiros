package ao.bolseiro.api.bolseiro.model;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ao.bolseiro.api.bolseiro.model.enumeration.Status;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_RETORNO")
public class Retorno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRetorno;
	private String tipoRetorno;
	private String categoriaRetorno;
	private String duracaoRetorno;
	private LocalDate dataInicioRetorno;
	private LocalDate dataFimRetorno;
	@Enumerated(EnumType.STRING)
	private Status statusRetorno;


	
	public Retorno() {
	}



	public Retorno(String tipoRetorno, String categoriaRetorno, String duracaoRetorno,
			LocalDate dataInicioRetorno, LocalDate dataFimRetorno, Status statusRetorno) {
		this.tipoRetorno = tipoRetorno;
		this.categoriaRetorno = categoriaRetorno;
		this.duracaoRetorno = duracaoRetorno;
		this.dataInicioRetorno = dataInicioRetorno;
		this.dataFimRetorno = dataFimRetorno;
		this.statusRetorno = statusRetorno;
	}
	
	

}


