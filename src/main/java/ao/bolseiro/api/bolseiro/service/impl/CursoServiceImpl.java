package ao.bolseiro.api.bolseiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.CursoDto;
import ao.bolseiro.api.bolseiro.dto.cadastro.CursoCadastrarDto;
import ao.bolseiro.api.bolseiro.dto.response.ObjectoDeRetorno;
import ao.bolseiro.api.bolseiro.dto.response.ResponseCode;
import ao.bolseiro.api.bolseiro.dto.response.RestDataReturnDTO;
import ao.bolseiro.api.bolseiro.model.Curso;
import ao.bolseiro.api.bolseiro.repository.CursoRepository;
import ao.bolseiro.api.bolseiro.service.CursoService;
import ao.bolseiro.api.bolseiro.util.Utils;

@Service
public class CursoServiceImpl implements CursoService{
	
	
	
	@Autowired
	private CursoRepository cursoRepo;
	

	@Override
	public ResponseEntity<RestDataReturnDTO> salvar(CursoCadastrarDto curso) {

		Curso curs = convertToEntity(curso);
		
		curs.setNomeCurso(curso.getNomeCurso());
		curs.setCategoriaCurso(curso.getCategoriaCurso());
		curs.setDuracaoCurso(curso.getDuracaoCurso());
		curs.setFaculdadeCurso(curso.getFaculdadeCurso());
		curs.setInstituicaoEnsinoCurso(curso.getInstituicaoEnsinoCurso());
		
		Curso curso_salvo = cursoRepo.save(curs);

		return ObjectoDeRetorno.MensagemDeRetorno(curso_salvo, 1, ResponseCode.SUCESSO.getDescricao(),
				"Curso salvo com sucesso");
	}

	
	
	@Override
	public CursoCadastrarDto convertToDTO(Curso curso) {

		CursoCadastrarDto cursoDTO = new CursoCadastrarDto();
		Utils.copyObjecto(curso, cursoDTO);

		cursoDTO.setNomeCurso(curso.getNomeCurso());
		cursoDTO.setCategoriaCurso(curso.getCategoriaCurso());
		cursoDTO.setDuracaoCurso(curso.getDuracaoCurso());
		cursoDTO.setFaculdadeCurso(curso.getFaculdadeCurso());
		cursoDTO.setInstituicaoEnsinoCurso(curso.getInstituicaoEnsinoCurso());
		
		return cursoDTO;

	}

	
	@Override
	public Curso convertToEntity(CursoCadastrarDto curso) {

		Curso curs = new Curso();
		Utils.copyObjecto(curso, curs);

		return curs;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Page<CursoDto> listar(String nomeCurso, Pageable paginacao) {
		Page<Curso> curso;
		if (nomeCurso == null) {
			curso = cursoRepo.findAll(paginacao);
		} else {
			curso = cursoRepo.findByNomeCurso(nomeCurso, paginacao);
		}

		if (curso.isEmpty()) {
			return (Page<CursoDto>) ObjectoDeRetorno.MensagemDeRetorno(curso, 1, ResponseCode.INFORMACAO.getDescricao(),
					"O Curso com este nome não existe");
		}

		return CursoDto.converter(curso);
	}

}
