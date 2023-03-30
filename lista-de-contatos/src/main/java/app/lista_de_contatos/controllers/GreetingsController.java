package app.lista_de_contatos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
public class GreetingsController {

	@Autowired
	private ContatoRepository contatoRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "{id_user}/todos-contatos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> listar(@PathVariable Long id_user) {

		if (id_user != null) {

			Optional<Usuario> user = userRepository.findById(id_user);

			if (user.isPresent()) {

				Usuario usuario = (Usuario) user.orElse(null);

				List<Contato> contatosComUser = contatoRepository.findByUsuario(usuario);
				List<Contato> contatosSemUser = new ArrayList<>();

				for (Contato contato : contatosComUser) {

					contato.setUsuario(null);
					contatosSemUser.add(contato);

				}

				return new ResponseEntity<List<Contato>>(contatosSemUser, HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>("Verifique o caminho", HttpStatus.OK);

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

	
	@PostMapping(value = "novo-usuario")
	@ResponseBody
	public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {

		userRepository.save(usuario);

		return new ResponseEntity<String>("Usuario Criado!", HttpStatus.OK);
	}

	
	@GetMapping(value = "usuarios")
	@ResponseBody
	public ResponseEntity<?> listarUsuario() {

		List<Usuario> usuariosComContato = userRepository.findAll();
		if (usuariosComContato.isEmpty())
		{

			return new ResponseEntity<String>("Não existe usuários cadastrados!", HttpStatus.OK);
		}

		
		List<Usuario> usuariosSemContato = new ArrayList<>();
		for (Usuario usuario : usuariosComContato) 
		{
			
			usuario.setContatos(null);
			usuariosSemContato.add(usuario);
		}

		return new ResponseEntity<List<Usuario>>(usuariosSemContato, HttpStatus.OK);

		

	}
	@DeleteMapping(value = "apagar-usuario/{id}")
	@ResponseBody
	public ResponseEntity<?> apagarUsuario(@PathVariable(name="id") Long id) {

		Optional<Usuario> usuario = userRepository.findById(id);
		if (!usuario.isEmpty())
		{
			
			userRepository.deleteById(id);
	
			
			return new ResponseEntity<String>("Usuário Excluido!", HttpStatus.OK);
		}

		

		return new ResponseEntity<String>("Usuario não existe!", HttpStatus.OK);
		

	}

}
