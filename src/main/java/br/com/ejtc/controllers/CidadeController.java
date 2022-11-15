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

import br.com.ejtc.model.Cidade;
import br.com.ejtc.model.Estado;
import br.com.ejtc.services.CidadeService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {
	
	@Autowired
    private CidadeService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Cidade>> findAll() {
        Collection<Cidade> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codCidade}", method = RequestMethod.GET)
    public ResponseEntity<Cidade> find(@PathVariable Integer codCidade) {
        Cidade obj = service.findById(codCidade);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cidade> insert(@Valid @RequestBody Cidade obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codCidade}", method = RequestMethod.PUT)
    public ResponseEntity<Cidade> update(@Valid @RequestBody Cidade obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codCidade}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codCidade) {
        service.delete(codCidade);
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="/findByEstado/{codEstado}", method=RequestMethod.GET)
	public ResponseEntity<Collection<Cidade>> findByUf(@PathVariable Integer codEstado) {
		Estado obj = new Estado();
		obj.setCodEstado(codEstado);
		Collection<Cidade> collection = service.findByEstado(obj);
		return ResponseEntity.ok().body(collection);
	}

}
