package ao.bolseiro.api.bolseiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name = "TB_CURSO")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
	private String nomeCurso;
	private String categoriaCurso;
	private String duracaoCurso;
	private String faculdadeCurso;
	private String instituicaoEnsinoCurso;

	


	public Curso() {
	}

	
	public Curso(String nomeCurso, String categoriaCurso, String duracaoCurso, String faculdadeCurso,
			String instituicaoEnsinoCurso) {
		
		this.nomeCurso = nomeCurso;
		this.categoriaCurso = categoriaCurso;
		this.duracaoCurso = duracaoCurso;
		this.faculdadeCurso = faculdadeCurso;
		this.instituicaoEnsinoCurso = instituicaoEnsinoCurso;
	}
}
