package ao.bolseiro.api.bolseiro.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ao.bolseiro.api.bolseiro.repository.UsuarioRepository;
import ao.bolseiro.api.bolseiro.service.AutenticacaoService;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService userDetailsService;
	@Autowired	
	private TokenService tokenService;
	@Autowired	
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	//Autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.cors().and()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/Usuario/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Usuario/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Instituicao/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Instituicao/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Bolseiro/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Bolseiro/*").permitAll()
	
		.antMatchers(HttpMethod.GET, "/Bolsa/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Bolsa/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Curso/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Curso/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Actividade/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Actividade/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Tarefa/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Tarefa/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Retorno/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Retorno/*").permitAll()
		
		.antMatchers(HttpMethod.GET, "/Avaliacao/*").permitAll()
		.antMatchers(HttpMethod.POST, "/Avaliacao/*").permitAll()
		
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoPorTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	//Recursos
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html","/v2/api-docs","/webjars/**","/configuration/**","/swagger-resources/**");
	}
	
	
	/*
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("alunougs2023"));
	}*/
}
