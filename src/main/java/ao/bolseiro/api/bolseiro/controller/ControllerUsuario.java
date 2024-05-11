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

import ao.bolseiro.api.bolseiro.dto.UsuarioDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.UsuarioCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.UsuarioDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.service.UsuarioService;

@RestController
@RequestMapping("/Usuario")
public class ControllerUsuario {

	@Autowired
	private UsuarioService service;

	@CrossOrigin("*")
	@PostMapping("/salvar")
	public ResponseEntity<RestDataReturnDTO> salvar(@RequestBody @Valid UsuarioCadastrarDto cadastro) {

		return service.salvar(cadastro);
	}

	@GetMapping("/listar")
	public ResponseEntity<Page<UsuarioDto>> listar(@RequestParam(required = false) String nomeUsuario,
			Pageable paginacao) {
		Page<UsuarioDto> usuarios = service.listar(nomeUsuario, paginacao);
		return ResponseEntity.ok(usuarios);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid UsuarioDtoActualizar form) {
		ResponseEntity<UsuarioDto> response = service.atualizar(id, form);
		return response;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		ResponseEntity<Void> response = service.remover(id);
		return response;
	}
}
