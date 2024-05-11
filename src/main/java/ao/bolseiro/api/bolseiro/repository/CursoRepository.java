package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ao.bolseiro.api.bolseiro.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Page<Curso> findByNomeCurso(String nomeCurso, Pageable paginacao);

}
