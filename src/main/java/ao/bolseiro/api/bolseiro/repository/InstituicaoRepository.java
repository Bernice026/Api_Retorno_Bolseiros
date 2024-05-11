package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ao.bolseiro.api.bolseiro.model.Instituicao;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long>{

	Page<Instituicao> findByNomeInstituicao(String nomeInstituicao, Pageable paginacao);

}
