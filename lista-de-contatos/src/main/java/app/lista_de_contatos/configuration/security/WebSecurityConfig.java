package app.lista_de_contatos.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailServiceImp userDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.
		httpBasic()

		.and()
		.authorizeRequests()
		.anyRequest().authenticated();
	//	.anyRequest().permitAll(); || .anyRequest().authenticated();
        http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth
			.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder());
		
		
		/*
			.inMemoryAuthentication()
			.withUser("danison")
			.password(passwordEncoder().encode("3264766"))
			.roles("ADMIN");
		*/
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
 


	

}
