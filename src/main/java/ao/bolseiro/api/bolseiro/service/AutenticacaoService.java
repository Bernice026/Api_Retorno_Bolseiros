package ao.bolseiro.api.bolseiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.bolseiro.api.bolseiro.model.Usuario;
import ao.bolseiro.api.bolseiro.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findNomeUsuarioOrEmail(username);
		if(usuario.isPresent()) {
			return usuario;
		}
		throw new UsernameNotFoundException("Dados Invalidos");
	}

	
}
