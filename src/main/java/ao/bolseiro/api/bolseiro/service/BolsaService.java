package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.BolsaDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolsaCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Bolsa;


@Service
public interface BolsaService {

	ResponseEntity<RestDataReturnDTO> salvar(BolsaCadastrarDto bolsa);

	BolsaCadastrarDto convertToDTO(Bolsa bolsa);

	Bolsa convertToEntity(BolsaCadastrarDto bolsa);

	Page<BolsaDto> listar(String nomeBolsa, Pageable paginacao);

}
