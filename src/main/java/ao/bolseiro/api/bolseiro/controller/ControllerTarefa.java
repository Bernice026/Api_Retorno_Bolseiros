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

import ao.bolseiro.api.bolseiro.dto.TarefaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.TarefaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.TarefaService;

@RestController
@RequestMapping("/Tarefa")
public class ControllerTarefa {
	
	
	@Autowired
	private TarefaService service;
	
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid TarefaCadastrarDto tarefa) {
		return service.salvar(tarefa);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<TarefaDto>> listar(@RequestParam(required = false) String nomeTarefa,
			Pageable paginacao) {
		Page<TarefaDto> tarefa = service.listar(nomeTarefa, paginacao);
		return ResponseEntity.ok(tarefa);
	}
	
	
	

}
