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

import ao.bolseiro.api.bolseiro.dto.CursoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.CursoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.CursoService;

@RestController
@RequestMapping("/Curso")
public class ControllerCurso {
	
	
	
	@Autowired
	private CursoService service;
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid CursoCadastrarDto curso) {
		return service.salvar(curso);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<CursoDto>> listar(@RequestParam(required = false) String nomeCurso,
			Pageable paginacao) {
		Page<CursoDto> curso = service.listar(nomeCurso, paginacao);
		return ResponseEntity.ok(curso);
	}

}
