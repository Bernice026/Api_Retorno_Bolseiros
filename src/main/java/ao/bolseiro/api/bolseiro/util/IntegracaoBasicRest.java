package ao.bolseiro.api.bolseiro.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class IntegracaoBasicRest {
	
	public static WebClient webClientGetBuild(String user,String password,String url) {
		return  WebClient.builder()
				.baseUrl(url)
	            .filter(ExchangeFilterFunctions.basicAuthentication(user, password))
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();
	}
	
	public static WebClient webClientGetBuild(String url) {
		return  WebClient.builder()
				.baseUrl(url)
	            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	            .build();
	}

	//CRIAR REFERENCIA
	public static HttpResponse<JsonNode> uniRestGerarReferencia(String reference, String key) {
			 return Unirest
		    .post("https://api.proxypay.co.ao/references")
			.basicAuth("api", key)
			.header("accept", "application/vnd.proxypay.v1+json")
			.header("Content-Type", "application/json")
			.body(reference).asJson();
			 
			 
	}
	
	//BUSCAR REFERENCIAS POR ID
	public static HttpResponse<JsonNode> uniRestBuscarPorId(String reference,String key) {
		 return Unirest
	    .get("https://api.proxypay.co.ao/references/"+reference)
		.basicAuth("api", key)
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
    }
	
	//BUSCA PERSONALIZADA
	public static HttpResponse<JsonNode> uniRestBuscaPersonalizada(Integer offset,Integer limit,String status,String q,String key) {
		//https://api.proxypay.co.ao/references?offset=0&limit=20&q=2020565654
		 return Unirest
	    .get("https://api.proxypay.co.ao/references?offset="+offset+"&limit="+limit+"&status="+status+"&q="+q)
		.basicAuth("api", key)
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
   }
	
	
	
	
	//BUSCAR EVENTUS DE PAGAMENTO
	//https://api.proxypay.co.ao/events/payments
    public static HttpResponse<JsonNode> uniRestBuscarPagamentoNaFila(String key) {
		 return Unirest
	    .get("https://api.proxypay.co.ao/events/payments")
		.basicAuth("api", key)
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
   }
    
   public static HttpResponse<JsonNode> uniRestRetirarDaFila(String referenciaId,String key) {
		 return Unirest
	    .delete("https://api.proxypay.co.ao/events/payments/"+referenciaId)
		.basicAuth("api", key)
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
   }
   
   
	
	//LISTAR POR QUERYPARAMS - 001
	public static HttpResponse<JsonNode> uniRestListar(Integer offset,String status,String custom,String key) {
		return Unirest
	    .get("https://api.proxypay.co.ao/references?limit="+20+"&offset="+offset+"&status="+status+"&q="+custom)
	    .basicAuth("api", key)
   	    .header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
   }
	
	//LISTAR POR QUERYPARAMS - 002
	public static HttpResponse<JsonNode> uniRestListar(Integer offset,String status,String key) {
		return Unirest
	    .get("https://api.proxypay.co.ao/references?limit="+20+"&offset="+offset+"&status="+status)
	    .basicAuth("api", key)
	    .header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
	    .asJson();
	}
	
	//LISTAR POR QUERYPARAMS - 003
	public static HttpResponse<JsonNode> uniRestListar(Integer offset,String key) {
		return Unirest
	    .get("https://api.proxypay.co.ao/references?limit="+20+"&offset="+offset)
		.basicAuth("api", key)
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json")
		.asJson();
	}
}