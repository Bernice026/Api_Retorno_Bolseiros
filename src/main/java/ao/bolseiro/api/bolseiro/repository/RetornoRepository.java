package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Retorno;

@Repository
public interface RetornoRepository extends JpaRepository<Retorno, Long> {

	Page<Retorno> findByTipoRetorno(String tipoRetorno, Pageable paginacao);
	
	
	

}
