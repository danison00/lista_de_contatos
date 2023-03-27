package app.lista_de_contatos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.lista_de_contatos.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String username);

}
