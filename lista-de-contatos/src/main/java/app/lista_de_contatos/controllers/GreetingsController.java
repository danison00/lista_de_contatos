package app.lista_de_contatos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import app.lista_de_contatos.model.Contato;
import app.lista_de_contatos.model.Usuario;
import app.lista_de_contatos.repository.ContatoRepository;
import app.lista_de_contatos.repository.UserRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "{id_user}/todos-contatos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> listar(@PathVariable Long id_user) {

		Optional<Usuario> user = userRepository.findById(id_user);

		if (user.isPresent()) {

			Usuario usuario = (Usuario) user.orElse(null);

			List<Contato> contatosComUser = contatoRepository.findByUsuario(usuario);
			List<Object[]> contatosSemUser = new ArrayList<>();

			for (Contato contato : contatosComUser) {
				
				String cont[] = {contato.getNome(), contato.getTelefone(), contato.getEmail()};
				contatosSemUser.add(cont);

			}

			return new ResponseEntity<List<Object[]>>(contatosSemUser, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Usuario não encontrado", HttpStatus.OK);

	}

	@PostMapping(value = "{id_user}/salvar-contato")
	@ResponseBody
	public ResponseEntity<String> salvar(@RequestBody Contato contato, @PathVariable Long id_user) {

		Optional<Usuario> user = userRepository.findById(id_user);

		if (user.isPresent()) {

			Usuario usuario = user.orElse(null);
			contato.setUsuario(usuario);

			contatoRepository.save(contato);

			return new ResponseEntity<String>("Contato Salvo!", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>("Usuario não encontrado", HttpStatus.NOT_FOUND);

	}

	@DeleteMapping(value = "apagar-contato")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam(name = "id") Long id) {

		contatoRepository.deleteById(id);

		return new ResponseEntity<String>("Contato Excluído!", HttpStatus.OK);

	}

	@PutMapping(value = "atualizar-contato")
	@ResponseBody
	public ResponseEntity<String> atualizar(@RequestBody Contato contato) {

		contatoRepository.saveAndFlush(contato);

		return new ResponseEntity<String>("Contato Atualizado!", HttpStatus.OK);

	}
	
}
