package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.AvaliacaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.AvaliacaoCadastroDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Avaliacao;

@Service
public interface AvaliacaoService {

	ResponseEntity<RestDataReturnDTO> salvar(AvaliacaoCadastroDto avaliacao);

	AvaliacaoCadastroDto convertToDTO(Avaliacao avaliacao);

	Avaliacao convertToEntity(AvaliacaoCadastroDto avaliacao);

	Page<AvaliacaoDto> listar(String nomeAvaliacao, Pageable paginacao);

}
