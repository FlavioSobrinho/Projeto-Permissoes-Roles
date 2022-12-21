package br.com.cruzvita.projetopermissoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import br.com.cruzvita.projetopermissoes.service.PessoaService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity dto) throws Exception {
		dto.authorizeHttpRequests((request) -> request.
				requestMatchers("/", "/home").
				permitAll().
				anyRequest().
				authenticated()).
		formLogin((form) -> form.
				loginPage("/login").
				permitAll()).
		logout((logout) -> logout.
				permitAll());
		
		return dto.build();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService().passwordEncoder();
	}
	@Autowired
	public void PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
