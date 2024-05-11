package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.TarefaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.TarefaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Tarefa;

@Service
public interface TarefaService {

	ResponseEntity<RestDataReturnDTO> salvar(TarefaCadastrarDto tarefa);

	TarefaCadastrarDto convertToDTO(Tarefa tarefa);

	Tarefa convertToEntity(TarefaCadastrarDto tarefa);

	Page<TarefaDto> listar(String nomeTarefa, Pageable paginacao);
	
	

}
