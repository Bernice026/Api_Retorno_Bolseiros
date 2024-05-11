package ao.bolseiro.api.bolseiro.config.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.dto.Funcionalidades;
import ao.bolseiro.api.bolseiro.dto.InstituicaoRetorno;
import ao.bolseiro.api.bolseiro.dto.LoginRetornoDTO;
import ao.bolseiro.api.bolseiro.dto.ModuloRetorno;
import ao.bolseiro.api.bolseiro.dto.PerfilFuncionalidadeDTO2;
import ao.bolseiro.api.bolseiro.model.FuncionalidadePerfil;
import ao.bolseiro.api.bolseiro.model.Usuario;
import ao.bolseiro.api.bolseiro.model.UsuarioPerfil;
import ao.bolseiro.api.bolseiro.repository.FuncionalidadePerfilRepo;
import ao.bolseiro.api.bolseiro.repository.UsuarioPerfilRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenService {

	@Value("${bolseiro.jwt.expiration}")
	private String expiration;

	@Value("${bolseiro.jwt.secret}")
	private String secret;

	// @Autowired
	// private UserModuloRepository usuarioModuloRepository;
	/*
	 * @Autowired private ModuloPerfilRepo moduloPerfilRepo;
	 */
	@Autowired
	private UsuarioPerfilRepository userPerfilRepo;
	@Autowired
	private FuncionalidadePerfilRepo funcPerfilRepo;

	public String gerarToken(Authentication authentication) {

		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

		LoginRetornoDTO loginRetornoDTO = new LoginRetornoDTO();

		loginRetornoDTO.setAdmin(usuarioLogado.isAdmin());
		loginRetornoDTO.setNome(usuarioLogado.getNome());
		loginRetornoDTO.setNomeUsuario(usuarioLogado.getNomeUsuario());
		loginRetornoDTO.setEmail(usuarioLogado.getEmail());

		Date actual = new Date();
		Date dataExp = new Date(actual.getTime() + Long.parseLong(expiration));

		List<ModuloRetorno> modulos = new ArrayList<ModuloRetorno>();

		// MODULOS E PERFIS QUE TEM
		ModuloRetorno moduloRetorno = new ModuloRetorno();
		List<PerfilFuncionalidadeDTO2> perfis = new ArrayList<PerfilFuncionalidadeDTO2>();

		InstituicaoRetorno instituicaoRetorno = new InstituicaoRetorno();

		Funcionalidades funcionalidade;
		List<Funcionalidades> funcionalidades = new ArrayList<Funcionalidades>();

		PerfilFuncionalidadeDTO2 perfil = new PerfilFuncionalidadeDTO2();

		List<UsuarioPerfil> userPerfil = this.userPerfilRepo.buscarPerfilUsuario(usuarioLogado.getIdUser());

		if (!userPerfil.isEmpty()) {

			for (UsuarioPerfil userPer : userPerfil) {

				perfil = new PerfilFuncionalidadeDTO2();
				perfil.setNome(userPer.getPerfil().getNome());
				perfil.setEstado(userPer.getPerfil().isEstado());

				List<FuncionalidadePerfil> perfilUser = this.funcPerfilRepo.buscarFuncionalidadeUsuario(userPer.getId(),
						usuarioLogado.getIdUser());

				funcionalidades = new ArrayList<Funcionalidades>();
				for (FuncionalidadePerfil b : perfilUser) {
					funcionalidade = new Funcionalidades();
					funcionalidade.setDescricao(b.getFuncionalidade().getDescricao());
					funcionalidade.setEstado(b.getFuncionalidade().isEstado());
					funcionalidades.add(funcionalidade);
				}
				perfil.setFuncionalidade(funcionalidades);

				perfis.add(perfil);

			}
			moduloRetorno.setPerfil(perfis);
			modulos.add(moduloRetorno);

		} else {
			moduloRetorno.setPerfil(new ArrayList<PerfilFuncionalidadeDTO2>());
			modulos.add(moduloRetorno);
		}

		instituicaoRetorno = new InstituicaoRetorno();
		instituicaoRetorno.setId(usuarioLogado.getInstituicao().getIdInstituicao());
		instituicaoRetorno.setNome(usuarioLogado.getInstituicao().getNomeInstituicao());

		loginRetornoDTO.setInstituicao(instituicaoRetorno);
		loginRetornoDTO.setPerfil(perfis);

		return Jwts.builder().claim("token", loginRetornoDTO).setSubject(usuarioLogado.getIdUser().toString())
				.setIssuedAt(actual).setExpiration(dataExp).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return String.valueOf(body.getSubject());

		// return Long.parseLong(body.getSubject());
	}
}
