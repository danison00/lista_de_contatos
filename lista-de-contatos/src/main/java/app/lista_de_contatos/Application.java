package app.lista_de_contatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    	System.out.println("\n\n\nsenha: "+new BCryptPasswordEncoder().encode("12345")+"\n\n\n\n\n");

    }
}
