package ao.bolseiro.api.bolseiro.dto;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Tarefa;
import lombok.Data;

@Data
public class TarefaDto {

	
	
	private Long idTarefa;
	private String nomeTarefa;
	private String tipoTarefa;
	private String cargaHorariaTarefa;
	private String objectivoTarefa;
	private String descricaoTarefa;
	
	
	
	public TarefaDto(Tarefa tarefa) {
		super();
		this.idTarefa = tarefa.getIdTarefa();
		this.nomeTarefa = tarefa.getNomeTarefa();
		this.tipoTarefa = tarefa.getTipoTarefa();
		this.cargaHorariaTarefa = tarefa.getCargaHorariaTarefa();
		this.objectivoTarefa = tarefa.getObjectivoTarefa();
		this.descricaoTarefa = tarefa.getDescricaoTarefa();
	}



	public static Page<TarefaDto> converter(Page<Tarefa> tarefa) {
		return tarefa.map(TarefaDto::new);
	}
	
	
	
	
}
