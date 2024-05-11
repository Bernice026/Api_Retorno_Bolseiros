package ao.bolseiro.api.bolseiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ao.bolseiro.api.bolseiro.dto.RetornoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.RetornoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.RetornoService;



@RestController
@RequestMapping("/Retorno")
public class ControllerRetorno {
	
	
	@Autowired
	private RetornoService service;
	
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid RetornoCadastrarDto retorno) {
		return service.salvar(retorno);
	}
	
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<RetornoDto>> listar(@RequestParam(required = false) String tipoRetorno,
			Pageable paginacao) {
		Page<RetornoDto> retorno = service.listar(tipoRetorno, paginacao);
		return ResponseEntity.ok(retorno);
	}

}
