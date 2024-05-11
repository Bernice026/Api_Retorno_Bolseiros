package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.BolseiroDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.BolseiroCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.form.BolseiroDtoActualizar;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Bolseiro;

@Service
public interface BolseiroService {

	ResponseEntity<RestDataReturnDTO> salvar(BolseiroCadastrarDto bolseiro);

	BolseiroCadastrarDto convertToDTO(Bolseiro bolseiro);

	Bolseiro convertToEntity(BolseiroCadastrarDto bolseiro);

	Page<BolseiroDto> listar(String nomeBolseiro, Pageable paginacao);

	ResponseEntity<BolseiroDto> atualizar(Long id, BolseiroDtoActualizar form);

	ResponseEntity<Void> remover(Long id);

}
