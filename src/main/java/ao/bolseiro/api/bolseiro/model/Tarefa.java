package ao.bolseiro.api.bolseiro.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "TB_TAREFA")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarefa;
	private String nomeTarefa;
	private String tipoTarefa;
	private String cargaHorariaTarefa;
	private String objectivoTarefa;
	private String descricaoTarefa;


	public Tarefa() {
	}


	public Tarefa(String nomeTarefa, String tipoTarefa, String cargaHorariaTarefa, String objectivoTarefa,
			String descricaoTarefa) {
		
		this.nomeTarefa = nomeTarefa;
		this.tipoTarefa = tipoTarefa;
		this.cargaHorariaTarefa = cargaHorariaTarefa;
		this.objectivoTarefa = objectivoTarefa;
		this.descricaoTarefa = descricaoTarefa;
	}
	
	
	

}

