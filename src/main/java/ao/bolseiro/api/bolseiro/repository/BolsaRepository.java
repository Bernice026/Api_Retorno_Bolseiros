package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Bolsa;

@Repository
public interface BolsaRepository extends JpaRepository<Bolsa, Long>{

	Page<Bolsa> findByNomeBolsa(String nomeBolsa, Pageable paginacao);

}
