package ao.bolseiro.api.bolseiro.model.enumeration;

public enum EstadoReferencia {
	ACTIVAS("active"),DELETEDAS("deleted"),EXPIRADAS("expired"),PAGAS("paid");
	
	private String descricao;
	
	private EstadoReferencia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
