package app.lista_de_contatos.controllers;


import java.util.List;

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
    public ResponseEntity<String>salvar(@RequestBody Contato contato) {
    	
    	contatoRepository.save(contato);
    	System.out.println(contato);
    	return new ResponseEntity<String>("Contato Salvo!",HttpStatus.CREATED);
   
    }
    
    
    @DeleteMapping(value="apagar-contato")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam(name="id") Long id){
  
    	
    	contatoRepository.deleteById(id);
    	
    	return new ResponseEntity<String>("Contato Excluído!",HttpStatus.OK); 
    	
    	
    }
    
    
    @PutMapping(value="atualizar-contato")
    @ResponseBody
    public ResponseEntity<String> atualizar(@RequestBody Contato contato){
    	
    	
    	contatoRepository.saveAndFlush(contato);
    	
    	return new ResponseEntity<String>("Contato Atualizado!",HttpStatus.OK);
    	
    	
    }
    
    


    
    
}
