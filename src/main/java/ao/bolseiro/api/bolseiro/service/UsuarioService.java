package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.UsuarioDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.UsuarioCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.UsuarioDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Usuario;

@Service
public interface UsuarioService {

	public ResponseEntity<RestDataReturnDTO> salvar(UsuarioCadastrarDto usuario);
	
	public UsuarioCadastrarDto convertToDTO(Usuario usuario);
	
	public Usuario convertToEntity(UsuarioCadastrarDto usuario);

	Page<UsuarioDto> listar(String nomeUsuario, Pageable paginacao);

	ResponseEntity<UsuarioDto> atualizar(Long id, UsuarioDtoActualizar form);

	ResponseEntity<Void> remover(Long id);
}
