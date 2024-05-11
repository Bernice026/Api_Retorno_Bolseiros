package ao.bolseiro.api.bolseiro.config.validacao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String mensagem) {
        super(mensagem);
    }

}