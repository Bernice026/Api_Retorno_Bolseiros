package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.AvaliacaoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.AvaliacaoCadastroDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Avaliacao;
import ao.bolseiro.api.bolseiro.repository.AvaliacaoRepository;
import ao.bolseiro.api.bolseiro.service.AvaliacaoService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService{
	
	
	@Autowired
	private AvaliacaoRepository avaliacaoRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(AvaliacaoCadastroDto avaliacao) {

		Avaliacao avaliation = convertToEntity(avaliacao);

		avaliation.setNomeAvaliacao(avaliacao.getNomeAvaliacao());
		avaliation.setCargoAvaliacao(avaliacao.getCargoAvaliacao());
		avaliation.setDesempenhoAvaliacao(avaliacao.getDesempenhoAvaliacao());
		avaliation.setInterpessoalSkillAvaliacao(avaliacao.getInterpessoalSkillAvaliacao());
		avaliation.setTecnicaSkillAvaliacao(avaliacao.getTecnicaSkillAvaliacao());
		avaliation.setObjectivoAtingidoAvaliacao(avaliacao.getObjectivoAtingidoAvaliacao());
		avaliation.setStatusAvaliacao(avaliacao.getStatusAvaliacao());
		avaliation.setComentarioAvaliacao(avaliacao.getComentarioAvaliacao());

		Avaliacao avaliacao_salva = avaliacaoRepo.save(avaliation);

		return ObjectoDeRetorno.MensagemDeRetorno(avaliacao_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Avaliação salva com sucesso");
	}

	
	@Override
	public AvaliacaoCadastroDto convertToDTO(Avaliacao avaliacao) {

		AvaliacaoCadastroDto avaliacaoDTO = new AvaliacaoCadastroDto();
		Utils.copyObjecto(avaliacao, avaliacaoDTO);

		avaliacaoDTO.setNomeAvaliacao(avaliacao.getNomeAvaliacao());
		avaliacaoDTO.setCargoAvaliacao(avaliacao.getCargoAvaliacao());
		avaliacaoDTO.setDesempenhoAvaliacao(avaliacao.getDesempenhoAvaliacao());
		avaliacaoDTO.setInterpessoalSkillAvaliacao(avaliacao.getInterpessoalSkillAvaliacao());
		avaliacaoDTO.setTecnicaSkillAvaliacao(avaliacao.getTecnicaSkillAvaliacao());
		avaliacaoDTO.setObjectivoAtingidoAvaliacao(avaliacao.getObjectivoAtingidoAvaliacao());
		avaliacaoDTO.setStatusAvaliacao(avaliacao.getStatusAvaliacao());
		avaliacaoDTO.setComentarioAvaliacao(avaliacao.getComentarioAvaliacao());

		return avaliacaoDTO;

	}

	@Override
	public Avaliacao convertToEntity(AvaliacaoCadastroDto avaliacao) {

		Avaliacao avaliation = new Avaliacao();
		Utils.copyObjecto(avaliacao, avaliation);

		return avaliation;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<AvaliacaoDto> listar(String nomeAvaliacao, Pageable paginacao) {
		Page<Avaliacao> avaliacao;
		if (nomeAvaliacao == null) {
			avaliacao = avaliacaoRepo.findAll(paginacao);
		} else {
			avaliacao = avaliacaoRepo.findByNomeAvaliacao(nomeAvaliacao, paginacao);
		}

		if (avaliacao.isEmpty()) {
			return (Page<AvaliacaoDto>) ObjectoDeRetorno.MensagemDeRetorno(avaliacao, 1,
					ResponseCode.INFORMACAO.getDescricao(), "O tipo de retorno que procura, não existe");
		}
		return AvaliacaoDto.converter(avaliacao);
	}

}
