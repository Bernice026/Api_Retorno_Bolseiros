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

import ao.bolseiro.api.bolseiro.dto.ActividadeDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.ActividadeCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.ActividadeService;


@RestController
@RequestMapping("/Actividade")
public class ControllerActividade {
	
	
	@Autowired
	private ActividadeService service;
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid ActividadeCadastrarDto actividade) {
		return service.salvar(actividade);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<ActividadeDto>> listar(@RequestParam(required = false) String nomeActividade,
			Pageable paginacao) {
		Page<ActividadeDto> actividade = service.listar(nomeActividade, paginacao);
		return ResponseEntity.ok(actividade);
	}

	
}
