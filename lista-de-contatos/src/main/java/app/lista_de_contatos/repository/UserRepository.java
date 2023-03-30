package app.lista_de_contatos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.lista_de_contatos.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);
	
	//@Query("DELETE FROM usuario WHERE id = :id ON CASCADE")
	//boolean apagarUsuario(Long id);

}
