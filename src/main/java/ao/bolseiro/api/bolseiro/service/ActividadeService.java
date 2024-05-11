package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.ActividadeDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.ActividadeCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Actividade;

@Service
public interface ActividadeService {

	ResponseEntity<RestDataReturnDTO> salvar(ActividadeCadastrarDto actividade);

	ActividadeCadastrarDto convertToDTO(Actividade actividade);

	Actividade convertToEntity(ActividadeCadastrarDto actividade);

	Page<ActividadeDto> listar(String nomeActividade, Pageable paginacao);

}
