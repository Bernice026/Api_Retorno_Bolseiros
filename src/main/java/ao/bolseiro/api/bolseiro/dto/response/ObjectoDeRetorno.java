package ao.bolseiro.api.bolseiro.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ObjectoDeRetorno {
	public static ResponseEntity<RestDataReturnDTO> MensagemDeRetorno(Object data, int quantidadeTotalItens,Integer codigo,String mensagem) {
		RestDataReturnDTO resultado = new RestDataReturnDTO();
		//DEFINE A MENSAGEM DE RETORNO.
		RestMessageReturnDTO retorno=new RestMessageReturnDTO(codigo,mensagem);
		resultado.setRetorno(retorno);
		
		//DEFINE A ESTRUTURA DO OBJECTO
		resultado.setData(data);
		resultado.setQuantidadeTotalItens(quantidadeTotalItens);
		return new ResponseEntity<RestDataReturnDTO>(resultado, HttpStatus.OK);
	}
	
	public static ResponseEntity<RestDataReturnDTO> ok(Object data,int quantidadeTotalItens) {
		RestDataReturnDTO resultado = new RestDataReturnDTO();
		//DEFINE A MENSAGEM DE RETORNO.
		RestMessageReturnDTO retorno=new RestMessageReturnDTO(200,"Operação realizada com sucesso!");
		resultado.setRetorno(retorno);
		
		//DEFINE A ESTRUTURA DO OBJECTO
		resultado.setData(data);
		resultado.setQuantidadeTotalItens(quantidadeTotalItens);
		return new ResponseEntity<RestDataReturnDTO>(resultado, HttpStatus.OK);
	}
	
	public static ResponseEntity<RestDataReturnDTO> badRequest(Object data,int quantidadeTotalItens) {
		RestDataReturnDTO resultado = new RestDataReturnDTO();
		//DEFINE A MENSAGEM DE RETORNO.
		RestMessageReturnDTO retorno=new RestMessageReturnDTO(400,"Existe algum erro na requisição.");
		resultado.setRetorno(retorno);
		
		//DEFINE A ESTRUTURA DO OBJECTO
		resultado.setData(data);
		resultado.setQuantidadeTotalItens(quantidadeTotalItens);
		return new ResponseEntity<RestDataReturnDTO>(resultado, HttpStatus.OK);
	}
	
	//NOVO OBJECTO COM PAGINATOR
	public static ResponseEntity<RestDataReturnDTO> MensagemDeRetorno(Object data,Paginator paginator, int quantidadeTotalItens,Integer codigo,String mensagem) {
		RestDataReturnDTO resultado = new RestDataReturnDTO();
		//DEFINE A MENSAGEM DE RETORNO.
		RestMessageReturnDTO retorno=new RestMessageReturnDTO(codigo,mensagem);
		resultado.setRetorno(retorno);
		
		//DEFINE A ESTRUTURA DO OBJECTO
		resultado.setData(data);
		resultado.setQuantidadeTotalItens(quantidadeTotalItens);
		
		//SETAR OS DADOS DO PAGINATOR.
		resultado.setPaginator(paginator);
		return new ResponseEntity<RestDataReturnDTO>(resultado, HttpStatus.OK);
	}
}
