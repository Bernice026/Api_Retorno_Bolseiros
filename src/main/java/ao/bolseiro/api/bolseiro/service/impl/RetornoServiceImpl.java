package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.RetornoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.RetornoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Retorno;
import ao.bolseiro.api.bolseiro.repository.RetornoRepository;
import ao.bolseiro.api.bolseiro.service.RetornoService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class RetornoServiceImpl implements RetornoService{
	
	
	@Autowired
	private RetornoRepository retornoRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(RetornoCadastrarDto retorno) {

		Retorno returns = convertToEntity(retorno);

		returns.setTipoRetorno(retorno.getTipoRetorno());
		returns.setCategoriaRetorno(retorno.getCategoriaRetorno());
		returns.setDuracaoRetorno(retorno.getDuracaoRetorno());
		returns.setDataInicioRetorno(retorno.getDataInicioRetorno());
		returns.setDataFimRetorno(retorno.getDataFimRetorno());
		returns.setStatusRetorno(retorno.getStatusRetorno());

		Retorno retorno_salvo = retornoRepo.save(returns);

		return ObjectoDeRetorno.MensagemDeRetorno(retorno_salvo, 1, ResponseCode.SUCESSO.getDescricao(),
				"Retorno salvo com sucesso");
	}

	
	@Override
	public RetornoCadastrarDto convertToDTO(Retorno retorno) {

		RetornoCadastrarDto retornoDTO = new RetornoCadastrarDto();
		Utils.copyObjecto(retorno, retornoDTO);

		retornoDTO.setTipoRetorno(retorno.getTipoRetorno());
		retornoDTO.setCategoriaRetorno(retorno.getCategoriaRetorno());
		retornoDTO.setDuracaoRetorno(retorno.getDuracaoRetorno());
		retornoDTO.setDataInicioRetorno(retorno.getDataInicioRetorno());
		retornoDTO.setDataFimRetorno(retorno.getDataFimRetorno());
		retornoDTO.setStatusRetorno(retorno.getStatusRetorno());

		return retornoDTO;

	}

	@Override
	public Retorno convertToEntity(RetornoCadastrarDto retorno) {

		Retorno returns = new Retorno();
		Utils.copyObjecto(retorno, returns);

		return returns;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<RetornoDto> listar(String tipoRetorno, Pageable paginacao) {
		Page<Retorno> retorno;
		if (tipoRetorno == null) {
			retorno = retornoRepo.findAll(paginacao);
		} else {
			retorno = retornoRepo.findByTipoRetorno(tipoRetorno, paginacao);
		}

		if (retorno.isEmpty()) {
			return (Page<RetornoDto>) ObjectoDeRetorno.MensagemDeRetorno(retorno, 1,
					ResponseCode.INFORMACAO.getDescricao(), "O tipo de retorno que procura, não existe");
		}
		return RetornoDto.converter(retorno);
	}

}
