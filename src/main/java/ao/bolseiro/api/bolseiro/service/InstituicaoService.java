package ao.bolseiro.api.bolseiro.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.InstituicaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.InstituicaoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.InstituicaoDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Instituicao;


@Service
public interface InstituicaoService {
	
	public ResponseEntity<RestDataReturnDTO> salvar(InstituicaoCadastrarDto instituicao);
	
	public InstituicaoCadastrarDto convertToDTO(Instituicao instituicao);
	
	public Instituicao convertToEntity(InstituicaoCadastrarDto instituicao);

	Page<InstituicaoDto> listar(String nomeInstituicao, Pageable paginacao);

	ResponseEntity<InstituicaoDto> atualizar(Long id, InstituicaoDtoActualizar form);

	ResponseEntity<Void> remover(Long id);

	
	

}
