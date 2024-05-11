package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.ActividadeDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.ActividadeCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Actividade;
import ao.bolseiro.api.bolseiro.repository.ActividadeRepository;
import ao.bolseiro.api.bolseiro.service.ActividadeService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class ActividadeServiceImpl implements ActividadeService {

	@Autowired
	private ActividadeRepository actividadeRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(ActividadeCadastrarDto actividade) {

		Actividade activity = convertToEntity(actividade);

		activity.setNomeActividade(actividade.getNomeActividade());
		activity.setTipoActividade(actividade.getTipoActividade());
		activity.setDuracaoActividade(actividade.getDuracaoActividade());
		activity.setDataInicioActividade(actividade.getDataInicioActividade());
		activity.setDataFimActividade(actividade.getDataFimActividade());
		activity.setDescricaoActividade(actividade.getDescricaoActividade());

		Actividade actividade_salva = actividadeRepo.save(activity);

		return ObjectoDeRetorno.MensagemDeRetorno(actividade_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Bolsa salva com sucesso");
	}

	
	@Override
	public ActividadeCadastrarDto convertToDTO(Actividade actividade) {

		ActividadeCadastrarDto actividadeDTO = new ActividadeCadastrarDto();
		Utils.copyObjecto(actividade, actividadeDTO);

		actividadeDTO.setNomeActividade(actividade.getNomeActividade());
		actividadeDTO.setTipoActividade(actividade.getTipoActividade());
		actividadeDTO.setDuracaoActividade(actividade.getDuracaoActividade());
		actividadeDTO.setDataInicioActividade(actividade.getDataInicioActividade());
		actividadeDTO.setDataFimActividade(actividade.getDataFimActividade());
		actividadeDTO.setDescricaoActividade(actividade.getDescricaoActividade());
		return actividadeDTO;
	}

	@Override
	public Actividade convertToEntity(ActividadeCadastrarDto actividade) {

		Actividade activity = new Actividade();
		Utils.copyObjecto(actividade, activity);

		return activity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ActividadeDto> listar(String nomeActividade, Pageable paginacao) {
		Page<Actividade> actividade;
		if (nomeActividade == null) {
			actividade = actividadeRepo.findAll(paginacao);
		} else {
			actividade = actividadeRepo.findByNomeActividade(nomeActividade, paginacao);
		}
		if (actividade.isEmpty()) {
			return (Page<ActividadeDto>) ObjectoDeRetorno.MensagemDeRetorno(actividade, 1,
					ResponseCode.INFORMACAO.getDescricao(), "A Actividade com este nome não existe");
		}
		return ActividadeDto.converter(actividade);
	}

}
