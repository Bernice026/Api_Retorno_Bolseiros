package ao.bolseiro.api.bolseiro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.bolseiro.api.bolseiro.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
	@Query(value = "SELECT * FROM TB_USUARIO WHERE NOME_USUARIO =:USER_NAME OR EMAIL =:USER_NAME", nativeQuery = true)
	Usuario findNomeUsuarioOrEmail(@Param("USER_NAME")String USER_NAME);
	
	@Query(value = "SELECT * FROM TB_USUARIO WHERE ID_USER =:ID", nativeQuery = true)
	Usuario findID(@Param("ID")String ID);

	boolean existsByEmail(String email);

	boolean existsByNome(String nome);

	Page<Usuario> findByNome(String nomeUsuario, Pageable paginacao);
	
}
