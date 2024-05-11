package ao.bolseiro.api.bolseiro.dto.cadastro;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.bolseiro.api.bolseiro.model.enumeration.Status;
import lombok.Data;

@Data
public class RetornoCadastrarDto {
	
	
	private String tipoRetorno;
	private String categoriaRetorno;
	private String duracaoRetorno;
	private LocalDate dataInicioRetorno;
	private LocalDate dataFimRetorno;
	@Enumerated(EnumType.STRING)
	private Status statusRetorno;

}
