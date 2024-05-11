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

import ao.bolseiro.api.bolseiro.dto.BolsaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolsaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.BolsaService;

@RestController
@RequestMapping("/Bolsa")
public class ControllerBolsa {
	
	
	@Autowired
	private BolsaService service;
	
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid BolsaCadastrarDto bolsa) {
		return service.salvar(bolsa);
	}
	
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<BolsaDto>> listar(@RequestParam(required = false) String nomeBolsa,
			Pageable paginacao) {
		Page<BolsaDto> bolsa = service.listar(nomeBolsa, paginacao);
		return ResponseEntity.ok(bolsa);
	}

	
	


}
