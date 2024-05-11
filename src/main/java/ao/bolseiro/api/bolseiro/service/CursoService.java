package ao.bolseiro.api.bolseiro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.CursoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.CursoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Curso;

@Service
public interface CursoService {

	ResponseEntity<RestDataReturnDTO> salvar(CursoCadastrarDto curso);

	CursoCadastrarDto convertToDTO(Curso curso);

	Curso convertToEntity(CursoCadastrarDto curso);

	Page<CursoDto> listar(String nomeCurso, Pageable paginacao);

}
