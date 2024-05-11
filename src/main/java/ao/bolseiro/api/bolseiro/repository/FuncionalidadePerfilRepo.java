package ao.bolseiro.api.bolseiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.bolseiro.api.bolseiro.model.FuncionalidadePerfil;

public interface FuncionalidadePerfilRepo extends JpaRepository<FuncionalidadePerfil, Long> {

	@Query(value = "SELECT * FROM TB_PERFIL_FUNCIONALIDADE WHERE PERFIL_ID =:perfil AND USUARIO_ID =:usuario", nativeQuery = true)
	public List<FuncionalidadePerfil> buscarFuncionalidadeUsuario(@Param("perfil") Long perfil, @Param("usuario") Long usuario);
}
