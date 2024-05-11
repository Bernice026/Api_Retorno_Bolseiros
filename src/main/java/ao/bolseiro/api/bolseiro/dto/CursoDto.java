package ao.bolseiro.api.bolseiro.dto;

import org.springframework.data.domain.Page;

import ao.bolseiro.api.bolseiro.model.Curso;
import lombok.Data;

@Data
public class CursoDto {
	
	private Long idCurso;
	private String nomeCurso;
	private String categoriaCurso;
	private String duracaoCurso;
	private String faculdadeCurso;
	private String instituicaoEnsinoCurso;
	
	
	
	public CursoDto( Curso curso) {
		super();
		this.idCurso = curso.getIdCurso();
		this.nomeCurso = curso.getNomeCurso();
		this.categoriaCurso = curso.getCategoriaCurso();
		this.duracaoCurso = curso.getDuracaoCurso();
		this.faculdadeCurso = curso.getFaculdadeCurso();
		this.instituicaoEnsinoCurso = curso.getInstituicaoEnsinoCurso();
	}



	public static Page<CursoDto> converter(Page<Curso> curso) {
		return curso.map(CursoDto::new);
	}
	
	
	

}
