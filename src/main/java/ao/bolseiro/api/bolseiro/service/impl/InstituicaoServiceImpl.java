package ao.bolseiro.api.bolseiro.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.config.validacao.NaoEncontradoException;
import ao.bolseiro.api.bolseiro.dto.InstituicaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.InstituicaoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.InstituicaoDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Instituicao;
import ao.bolseiro.api.bolseiro.repository.InstituicaoRepository;
import ao.bolseiro.api.bolseiro.service.InstituicaoService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class InstituicaoServiceImpl implements InstituicaoService {

	@Autowired
	private InstituicaoRepository instituicaoRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(InstituicaoCadastrarDto instituicao) {

		Instituicao instituic = convertToEntity(instituicao);

		System.out.println("AQUI " + instituicao.getNomeInstituicao());

		/*
		 * Usuario usuarioPesquisa = this.usuarioRepo.findByEmail(usuario.getEmail());
		 * if (usuarioPesquisa != null) { return
		 * ObjectoDeRetorno.MensagemDeRetorno(null, 1,
		 * ResponseCode.INFORMACAO.getDescricao(), "Este email já existe"); }
		 */

		instituic.setNomeInstituicao(instituicao.getNomeInstituicao());
		instituic.setNifInstituicao(instituicao.getNifInstituicao());
		instituic.setTipoInstituicao(instituicao.getTipoInstituicao());
		instituic.setSectorInstituicao(instituicao.getSectorInstituicao());

		Instituicao instituic_salva = instituicaoRepo.save(instituic);

		return ObjectoDeRetorno.MensagemDeRetorno(instituic_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Instituicao salva com sucesso");
	}

	@Override
	public InstituicaoCadastrarDto convertToDTO(Instituicao instituicao) {

		InstituicaoCadastrarDto instituicaoDTO = new InstituicaoCadastrarDto();
		Utils.copyObjecto(instituicao, instituicaoDTO);

		instituicaoDTO.setNomeInstituicao(instituicao.getNomeInstituicao());
		instituicaoDTO.setNifInstituicao(instituicao.getNifInstituicao());
		instituicaoDTO.setTipoInstituicao(instituicao.getTipoInstituicao());
		instituicaoDTO.setSectorInstituicao(instituicao.getSectorInstituicao());

		return instituicaoDTO;

	}

	@Override
	public Instituicao convertToEntity(InstituicaoCadastrarDto instituicao) {

		Instituicao instituic = new Instituicao();
		Utils.copyObjecto(instituicao, instituic);

		return instituic;
	}
	
	

	@Override
	public Page<InstituicaoDto> listar(String nomeInstituicao, Pageable paginacao) {
		Page<Instituicao> instituicao;
		if (nomeInstituicao == null) {
			instituicao = instituicaoRepo.findAll(paginacao);
		} else {
			instituicao = instituicaoRepo.findByNomeInstituicao(nomeInstituicao, paginacao);
		}

		if (instituicao.isEmpty()) {
			throw new NaoEncontradoException("Nenhuma instituição com o nome especificado: " + nomeInstituicao + "foi encontrada!");
		}

		return InstituicaoDto.converter(instituicao);
	}
	
	
	

	@Override
	@Transactional
	public ResponseEntity<InstituicaoDto> atualizar(Long id, InstituicaoDtoActualizar form) {
		Optional<Instituicao> optional = instituicaoRepo.findById(id);
		if (optional.isPresent()) {
			Instituicao instituic = form.actualizar(id, instituicaoRepo);
			return ResponseEntity.ok(new InstituicaoDto(instituic));
		} else {
			throw new NaoEncontradoException("Instituição não encontrada: " + id);
		}
	}

	
	
	@Override
	@Transactional
	public ResponseEntity<Void> remover(Long id) {
		Optional<Instituicao> optional = instituicaoRepo.findById(id);
		if (optional.isPresent()) {
			instituicaoRepo.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			throw new NaoEncontradoException("Instituição não existe: " + id);
		}
	}

}
