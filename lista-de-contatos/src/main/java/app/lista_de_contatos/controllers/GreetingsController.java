package app.lista_de_contatos.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.lista_de_contatos.model.Contato;
import app.lista_de_contatos.repository.ContatoRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
 
	@Autowired
	private ContatoRepository contatoRepository;
	
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    
    @RequestMapping(value="listar-contatos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contato>> listar(){
    	
    	List<Contato> usuarios = contatoRepository.findAll();
    	
    	return new ResponseEntity<List<Contato>>(usuarios, HttpStatus.OK);
    	
    }
    
    
    
    @PostMapping(value="salvar-contato")
    @ResponseBody
    public ResponseEntity<Contato> salvar(@RequestBody Contato contato) {
    	
    	Contato cont = contatoRepository.save(contato);
    	return new ResponseEntity<Contato>(cont, HttpStatus.CREATED);
   
    }
    
    


    
    
}
