package ao.bolseiro.api.bolseiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.bolseiro.api.bolseiro.model.UsuarioPerfil;


public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long> {

	@Query(value = "SELECT * FROM TB_USER_PERFIL WHERE USUARIO_ID =:usuario", nativeQuery = true)
	public List<UsuarioPerfil> buscarPerfilUsuario(@Param("usuario") Long usuario);
}
