package ao.bolseiro.api.bolseiro.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import ao.bolseiro.api.bolseiro.model.Usuario;
import ao.bolseiro.api.bolseiro.repository.UsuarioRepository;

public class AutenticacaoPorTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoPorTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean tokenValido = tokenService.isTokenValido(token);
		if(tokenValido) {
			autenticarUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void autenticarUsuario(String token) {
		String idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findID(idUsuario);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
