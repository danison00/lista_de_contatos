package app.lista_de_contatos.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
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
 


	

}
