package ao.bolseiro.api.bolseiro.dto.cadastro;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ActividadeCadastrarDto {
	
	
	
	private String nomeActividade;
	private String tipoActividade;
	private String duracaoActividade;
	private LocalDate dataInicioActividade;
	private LocalDate dataFimActividade;
	private String descricaoActividade;

}
