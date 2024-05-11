package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

	Page<Avaliacao> findByNomeAvaliacao(String nomeAvaliacao, Pageable paginacao);

}
