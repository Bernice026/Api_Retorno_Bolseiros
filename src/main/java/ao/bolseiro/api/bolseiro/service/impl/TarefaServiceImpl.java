package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.TarefaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.TarefaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Tarefa;
import ao.bolseiro.api.bolseiro.repository.TarefaRepository;
import ao.bolseiro.api.bolseiro.service.TarefaService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class TarefaServiceImpl implements TarefaService {

	@Autowired
	private TarefaRepository tarefaRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(TarefaCadastrarDto tarefa) {

		Tarefa homeWork = convertToEntity(tarefa);

		homeWork.setNomeTarefa(tarefa.getNomeTarefa());
		homeWork.setTipoTarefa(tarefa.getTipoTarefa());
		homeWork.setCargaHorariaTarefa(tarefa.getCargaHorariaTarefa());
		homeWork.setObjectivoTarefa(tarefa.getObjectivoTarefa());
		homeWork.setDescricaoTarefa(tarefa.getDescricaoTarefa());

		Tarefa tarefa_salva = tarefaRepo.save(homeWork);

		return ObjectoDeRetorno.MensagemDeRetorno(tarefa_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Tarefa salva com sucesso");
	}

	@Override
	public TarefaCadastrarDto convertToDTO(Tarefa tarefa) {

		TarefaCadastrarDto tarefaDTO = new TarefaCadastrarDto();
		Utils.copyObjecto(tarefa, tarefaDTO);

		tarefaDTO.setNomeTarefa(tarefa.getNomeTarefa());
		tarefaDTO.setTipoTarefa(tarefa.getTipoTarefa());
		tarefaDTO.setCargaHorariaTarefa(tarefa.getCargaHorariaTarefa());
		tarefaDTO.setObjectivoTarefa(tarefa.getObjectivoTarefa());
		tarefaDTO.setDescricaoTarefa(tarefa.getDescricaoTarefa());

		return tarefaDTO;

	}

	@Override
	public Tarefa convertToEntity(TarefaCadastrarDto tarefa) {

		Tarefa homeWork = new Tarefa();
		Utils.copyObjecto(tarefa, homeWork);

		return homeWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<TarefaDto> listar(String nomeTarefa, Pageable paginacao) {
		Page<Tarefa> tarefa;
		if (nomeTarefa == null) {
			tarefa = tarefaRepo.findAll(paginacao);
		} else {
			tarefa = tarefaRepo.findByNomeTarefa(nomeTarefa, paginacao);
		}

		if (tarefa.isEmpty()) {
			return (Page<TarefaDto>) ObjectoDeRetorno.MensagemDeRetorno(tarefa, 1,
					ResponseCode.INFORMACAO.getDescricao(), "A Tarefa com este nome não existe");
		}

		return TarefaDto.converter(tarefa);
	}

}
