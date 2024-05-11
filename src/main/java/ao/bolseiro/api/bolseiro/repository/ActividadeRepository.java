package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Actividade;

@Repository
public interface ActividadeRepository extends JpaRepository<Actividade, Long>{

	Page<Actividade> findByNomeActividade(String nomeActividade, Pageable paginacao);

}
