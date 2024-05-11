package ao.bolseiro.api.bolseiro.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.config.validacao.NaoEncontradoException;
import ao.bolseiro.api.bolseiro.dto.BolseiroDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolseiroCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.BolseiroDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Bolseiro;
import ao.bolseiro.api.bolseiro.repository.BolseiroRepository;
import ao.bolseiro.api.bolseiro.service.BolseiroService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class BolseiroServiceImpl implements BolseiroService {
	
	@Autowired
	private BolseiroRepository bolseioRepo;

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(BolseiroCadastrarDto bolseiro) {

		Bolseiro bolse = convertToEntity(bolseiro);

		bolse.setTipoDocBolseiro(bolseiro.getTipoDocBolseiro());
		bolse.setCodDocBolseiro(bolseiro.getCodDocBolseiro());
		bolse.setEstudanteBolseiro(bolseiro.getEstudanteBolseiro());
		bolse.setNascimentoBolseiro(bolseiro.getNascimentoBolseiro());
		bolse.setNacionalidadeBolseiro(bolseiro.getNacionalidadeBolseiro());
		bolse.setNivelAcademicoBolseiro(bolseiro.getNivelAcademicoBolseiro());
		
		Bolseiro bolse_salva = bolseioRepo.save(bolse);

		return ObjectoDeRetorno.MensagemDeRetorno(bolse_salva, 1, ResponseCode.SUCESSO.getDescricao(),
				"Instituicao salva com sucesso");
	}

	
	
	@Override
	public BolseiroCadastrarDto convertToDTO(Bolseiro bolseiro) {

		BolseiroCadastrarDto bolseiroDTO = new BolseiroCadastrarDto();
		Utils.copyObjecto(bolseiro, bolseiroDTO);

		bolseiroDTO.setTipoDocBolseiro(bolseiro.getTipoDocBolseiro());
		bolseiroDTO.setCodDocBolseiro(bolseiro.getCodDocBolseiro());
		bolseiroDTO.setEstudanteBolseiro(bolseiro.getEstudanteBolseiro());
		bolseiroDTO.setNascimentoBolseiro(bolseiro.getNascimentoBolseiro());
		bolseiroDTO.setNacionalidadeBolseiro(bolseiro.getNacionalidadeBolseiro());
		bolseiroDTO.setNivelAcademicoBolseiro(bolseiro.getNivelAcademicoBolseiro());

		return bolseiroDTO;

	}

	
	@Override
	public Bolseiro convertToEntity(BolseiroCadastrarDto bolseiro) {

		Bolseiro bolse = new Bolseiro();
		Utils.copyObjecto(bolseiro, bolse);

		return bolse;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<BolseiroDto> listar(String documentoBolseiro, Pageable paginacao) {
		Page<Bolseiro> bolseiro;
		if (documentoBolseiro == null) {
			bolseiro = bolseioRepo.findAll(paginacao);
		} else {
			bolseiro = bolseioRepo.findByTipoDocBolseiro(documentoBolseiro, paginacao);
		}

		if (bolseiro.isEmpty()) {
			return (Page<BolseiroDto>) ObjectoDeRetorno.MensagemDeRetorno(bolseiro, 1, ResponseCode.INFORMACAO.getDescricao(),
					"O Bolseiro com este nome não existe");
		}

		return BolseiroDto.converter(bolseiro);
	}
	
	
	

	@Override
	@Transactional
	public ResponseEntity<BolseiroDto> atualizar(Long id, BolseiroDtoActualizar form) {
		Optional<Bolseiro> optional = bolseioRepo.findById(id);
		if (optional.isPresent()) {
			Bolseiro bolseiro = form.actualizar(id, bolseioRepo);
			return ResponseEntity.ok(new BolseiroDto(bolseiro));
		} else {
			throw new NaoEncontradoException("Instituição não encontrada: " + id);
		}
	}

	
	
	@Override
	@Transactional
	public ResponseEntity<Void> remover(Long id) {
		Optional<Bolseiro> optional = bolseioRepo.findById(id);
		if (optional.isPresent()) {
			bolseioRepo.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			throw new NaoEncontradoException("Instituição não existe: " + id);
		}
	}

}


