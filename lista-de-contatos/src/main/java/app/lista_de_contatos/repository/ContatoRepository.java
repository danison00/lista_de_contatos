package app.lista_de_contatos.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.lista_de_contatos.model.Contato;
import app.lista_de_contatos.model.Usuario;


@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

	List<Contato> findByUsuario(Usuario usuario);
	
	
	
	
}
