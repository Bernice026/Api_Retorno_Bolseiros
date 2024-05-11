package ao.bolseiro.api.bolseiro.dto;

import java.util.List;

import lombok.Data;

@Data
public class PerfilFuncionalidadeDTO2 {

	private String nome;
	private boolean estado;
	private List<Funcionalidades> funcionalidade;
}
