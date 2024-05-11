package ao.bolseiro.api.bolseiro.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.config.validacao.NaoEncontradoException;
import ao.bolseiro.api.bolseiro.dto.UsuarioDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.UsuarioCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.UsuarioDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Instituicao;
import ao.bolseiro.api.bolseiro.model.Usuario;
import ao.bolseiro.api.bolseiro.repository.UsuarioRepository;
import ao.bolseiro.api.bolseiro.service.UsuarioService;
import ao.bolseiro.api.bolseiro.util.Utils;

@ComponentScan
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(UsuarioCadastrarDto usuario) {

		Usuario user = convertToEntity(usuario);
		System.out.println("AQUI " + usuario.getEmail());

		/*
		 * Usuario usuarioPesquisa = this.usuarioRepo.findByEmail(usuario.getEmail());
		 * if (usuarioPesquisa != null) { return
		 * ObjectoDeRetorno.MensagemDeRetorno(null, 1,
		 * ResponseCode.INFORMACAO.getDescricao(), "Este email já existe"); }
		 */

		if (usuarioRepo.existsByEmail(usuario.getEmail())) {
			return ObjectoDeRetorno.MensagemDeRetorno(null, 1, ResponseCode.INFORMACAO.getDescricao(),
					"Já existe um usuário cadastrado com este e-mail.");
		}

		user.setLoginFalhado(0);
		user.setEstado(true);
		user.setValido(true);
		user.setDataCriacao(LocalDate.now());
		user.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		Usuario usuarioSalvo = usuarioRepo.save(user);

		return ObjectoDeRetorno.MensagemDeRetorno(usuarioSalvo, 1, ResponseCode.SUCESSO.getDescricao(),
				"Usuário salvo com sucesso");

	}

	@Override
	public UsuarioCadastrarDto convertToDTO(Usuario usuario) {

		UsuarioCadastrarDto usuarioDTO = new UsuarioCadastrarDto();
		Utils.copyObjecto(usuario, usuarioDTO);

		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setCodigoInstituicao(usuario.getInstituicao().getIdInstituicao());

		return usuarioDTO;

	}

	@Override
	public Usuario convertToEntity(UsuarioCadastrarDto usuario) {

		Usuario user = new Usuario();
		Utils.copyObjecto(usuario, user);

		Instituicao instituicao = new Instituicao(usuario.getCodigoInstituicao());
		user.setInstituicao(instituicao);

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<UsuarioDto> listar(String nomeUsuario, Pageable paginacao) {
		Page<Usuario> usuarios;
		if (nomeUsuario == null) {
			usuarios = usuarioRepo.findAll(paginacao);
		} else {
			usuarios = usuarioRepo.findByNome(nomeUsuario, paginacao);
		}

		if (usuarios.isEmpty()) {
			return (Page<UsuarioDto>) ObjectoDeRetorno.MensagemDeRetorno(null, 1, ResponseCode.INFORMACAO.getDescricao(),
					"Não existe um usuario com esse nome.");
		}

		return UsuarioDto.converter(usuarios);
	}

	@Override
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(Long id, UsuarioDtoActualizar form) {
		Optional<Usuario> optional = usuarioRepo.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.actualizar(id, usuarioRepo);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		} else {
			throw new NaoEncontradoException("Usuário não encontrado: " + id);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Void> remover(Long id) {
		Optional<Usuario> optional = usuarioRepo.findById(id);
		if (optional.isPresent()) {
			usuarioRepo.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			throw new NaoEncontradoException("Este usuário não existe: " + id);
		}
	}

}
