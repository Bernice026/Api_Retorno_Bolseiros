package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.RetornoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.RetornoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Retorno;

@Service
public interface RetornoService {

	ResponseEntity<RestDataReturnDTO> salvar(RetornoCadastrarDto retorno);

	RetornoCadastrarDto convertToDTO(Retorno retorno);

	Retorno convertToEntity(RetornoCadastrarDto retorno);

	Page<RetornoDto> listar(String tipoRetorno, Pageable paginacao);

}
