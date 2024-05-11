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

import ao.bolseiro.api.bolseiro.dto.AvaliacaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.AvaliacaoCadastroDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.AvaliacaoService;



@RestController
@RequestMapping("/Avaliacao")
public class ControllerAvaliacao {
	
	@Autowired
	private AvaliacaoService service;
	
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid AvaliacaoCadastroDto avaliacao) {
		return service.salvar(avaliacao);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<AvaliacaoDto>> listar(@RequestParam(required = false) String nomeAvaliacao,
			Pageable paginacao) {
		Page<AvaliacaoDto> avaliacao = service.listar(nomeAvaliacao, paginacao);
		return ResponseEntity.ok(avaliacao);
	}

}
