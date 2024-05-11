package ao.bolseiro.api.bolseiro.config.validacao;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NaoEncontradoException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
	
	
	
}

