package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Bolseiro;


@Repository
public interface BolseiroRepository extends JpaRepository<Bolseiro, Long>{

	Page<Bolseiro> findByTipoDocBolseiro(String documentoBolseiro, Pageable paginacao);

}
