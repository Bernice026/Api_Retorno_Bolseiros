package ao.bolseiro.api.bolseiro.dto.notificar;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DetalhesDaMensagemPorEnviarDTO {
	@NotNull
	@NotEmpty
	private String sender;
	@NotNull
	@NotEmpty
	private String texto;
	@NotNull
	@NotEmpty
	private List<String> destinarios=new ArrayList<String>();

}
