package ao.bolseiro.api.bolseiro.dto.response;

public enum ResponseCode {
	SUCESSO(200),REGRA_NEGOCIO(300),NENHUM_RESGISTRO(400),ERRO_INTERNO(500),INFORMACAO(600);

	private int descricao;

	ResponseCode(int descricao) {
		this.descricao = descricao;
	}

	public int getDescricao() {
		return descricao;
	}

	public void setDescricao(int descricao) {
		this.descricao = descricao;
	}
}
