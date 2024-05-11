package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

	Page<Tarefa> findByNomeTarefa(String nomeTarefa, Pageable paginacao);

}
