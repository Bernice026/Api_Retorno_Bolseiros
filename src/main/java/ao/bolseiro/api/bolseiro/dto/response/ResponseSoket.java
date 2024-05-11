package ao.bolseiro.api.bolseiro.dto.response;

public class ResponseSoket {
	private String message;
	private String loteSessaoId;

	public ResponseSoket(String message, String loteSessaoId) {
		this.message = message;
		this.loteSessaoId = loteSessaoId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLoteSessaoId() {
		return loteSessaoId;
	}

	public void setLoteSessaoId(String loteSessaoId) {
		this.loteSessaoId = loteSessaoId;
	}

	
}
