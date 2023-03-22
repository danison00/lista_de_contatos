package app.lista_de_contatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.lista_de_contatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
