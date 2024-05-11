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

import ao.bolseiro.api.bolseiro.dto.InstituicaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.InstituicaoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.InstituicaoDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.InstituicaoService;



@RestController
@RequestMapping("/Instituicao")
public class ControllerInstituicao {
	
	
	@Autowired
	private InstituicaoService service;
	
	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid InstituicaoCadastrarDto cadastro) {
		return service.salvar(cadastro);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<Page<InstituicaoDto>> listar(@RequestParam(required = false) String nomeInstituicao,
			Pageable paginacao) {
		Page<InstituicaoDto> instituicao = service.listar(nomeInstituicao, paginacao);
		return ResponseEntity.ok(instituicao);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<InstituicaoDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid InstituicaoDtoActualizar form) {
		ResponseEntity<InstituicaoDto> response = service.atualizar(id, form);
		return response;
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		ResponseEntity<Void> response = service.remover(id);
		return response;
	}

}
