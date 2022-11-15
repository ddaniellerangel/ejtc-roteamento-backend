package br.com.ejtc.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ejtc.model.Endereco;
import br.com.ejtc.services.EnderecoService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
    private EnderecoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Endereco>> findAll() {
        Collection<Endereco> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codEndereco}", method = RequestMethod.GET)
    public ResponseEntity<Endereco> find(@PathVariable Integer codEndereco) {
        Endereco obj = service.findById(codEndereco);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Endereco> insert(@Valid @RequestBody Endereco obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codEndereco}", method = RequestMethod.PUT)
    public ResponseEntity<Endereco> update(@Valid @RequestBody Endereco obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codEndereco}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codEndereco) {
        service.delete(codEndereco);
        return ResponseEntity.noContent().build();
    }
}
