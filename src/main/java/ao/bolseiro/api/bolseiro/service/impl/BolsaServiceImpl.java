package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.BolsaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolsaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Bolsa;
import ao.bolseiro.api.bolseiro.repository.BolsaRepository;
import ao.bolseiro.api.bolseiro.service.BolsaService;
import ao.bolseiro.api.bolseiro.util.Utils;


@Service
public class BolsaServiceImpl implements BolsaService{
	
	
	@Autowired
	private BolsaRepository bolsaRepo;
	

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(BolsaCadastrarDto bolsa) {

		Bolsa bols = convertToEntity(bolsa);
		
		bols.setNomeBolsa(bolsa.getNomeBolsa());
		bols.setCategoriaBolsa(bolsa.getCategoriaBolsa());
		bols.setTipoBolsa(bolsa.getTipoBolsa());
		bols.setDuracaoBolsa(bolsa.getDuracaoBolsa());
		bols.setDescricaoBolsa(bolsa.getDescricaoBolsa());
		
		Bolsa bolsa_salva = bolsaRepo.save(bols);

		return ObjectoDeRetorno.MensagemDeRetorno(bolsa_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Bolsa salva com sucesso");
	}

	
	
	@Override
	public BolsaCadastrarDto convertToDTO(Bolsa bolsa) {

		BolsaCadastrarDto bolsaDTO = new BolsaCadastrarDto();
		Utils.copyObjecto(bolsa, bolsaDTO);

		bolsaDTO.setNomeBolsa(bolsa.getNomeBolsa());
		bolsaDTO.setTipoBolsa(bolsa.getTipoBolsa());
		bolsaDTO.setCategoriaBolsa(bolsa.getCategoriaBolsa());
		bolsaDTO.setDuracaoBolsa(bolsa.getDuracaoBolsa());
		bolsaDTO.setDescricaoBolsa(bolsa.getDescricaoBolsa());
		
		return bolsaDTO;

	}

	
	@Override
	public Bolsa convertToEntity(BolsaCadastrarDto bolsa) {

		Bolsa bols = new Bolsa();
		Utils.copyObjecto(bolsa, bols);

		return bols;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<BolsaDto> listar(String nomeBolsa, Pageable paginacao) {
		Page<Bolsa> bolsa;
		if (nomeBolsa == null) {
			bolsa = bolsaRepo.findAll(paginacao);
		} else {
			bolsa = bolsaRepo.findByNomeBolsa(nomeBolsa, paginacao);
		}

		if (bolsa.isEmpty()) {
			return (Page<BolsaDto>) ObjectoDeRetorno.MensagemDeRetorno(bolsa, 1, ResponseCode.INFORMACAO.getDescricao(),
					"A Bolsa com este nome não existe");
		}

		return BolsaDto.converter(bolsa);
	}

}
