package br.mesttra.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthService authservice;

	
	public SecurityConfig(AuthService authservice) {
		this.authservice = authservice;
	}
	
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(authservice).passwordEncoder(this.passwordEncoder());
		}
	
//	// configuração de authenticação
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("mesttra").password(this.passwordEncoder().encode("123")).roles("ADMIN")
//			.and()
//			.withUser("aluno").password(this.passwordEncoder().encode("123")).roles("USER");
//	}

	// configuração da autorização das requisições
	// authenticated -> qualquer requisição tem que ser authenticacde
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().formLogin();
	}
	
	@Bean
	@Primary
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
