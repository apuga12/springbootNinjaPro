package com.industries.stark.springbootNinja.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// 1
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	// 2
	@Autowired
	public void configuredGlobal(AuthenticationManagerBuilder auth) throws Exception{
		// passwordEncoder => Nos ayuda a cifrar el passwd en BD
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	// 3
	// Source -> Overwrite/implement methods
	// Metodo a sobre escribir para cargar la configuracion
	protected void configure(HttpSecurity http) throws Exception {
		// Permite que se carguen los recursos estaticos
		http.authorizeRequests().antMatchers("/css/*","/imgs/*").permitAll()
		.anyRequest().authenticated()  // Todos los demas request, que sean autenticados
		.and()
		.formLogin().loginPage("/login") // Pagina de authentication
		.loginProcessingUrl("/logincheck")  // Path del controller que procesa el request
		.usernameParameter("username")  // User de nuestro pojo
		.passwordParameter("password")  // Pwd de nuestro pojo
		.defaultSuccessUrl("/loginsuccess").permitAll()
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
	}

}
