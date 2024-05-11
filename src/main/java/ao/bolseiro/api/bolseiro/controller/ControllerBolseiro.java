package ao.bolseiro.api.bolseiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ao.bolseiro.api.bolseiro.dto.BolseiroDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolseiroCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.BolseiroDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.BolseiroService;

@RestController
@RequestMapping("/Bolseiro")
public class ControllerBolseiro {
	
	
	
	@Autowired
	private BolseiroService service;
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid BolseiroCadastrarDto bolseiro) {
		return service.salvar(bolseiro);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<BolseiroDto>> listar(@RequestParam(required = false) String documentoBolseiro,
			Pageable paginacao) {
		Page<BolseiroDto> instituicao = service.listar(documentoBolseiro, paginacao);
		return ResponseEntity.ok(instituicao);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<BolseiroDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid BolseiroDtoActualizar form) {
		ResponseEntity<BolseiroDto> response = service.atualizar(id, form);
		return response;
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		ResponseEntity<Void> response = service.remover(id);
		return response;
	}


}

