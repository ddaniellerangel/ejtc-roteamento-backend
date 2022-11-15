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

import br.com.ejtc.model.Entrega;
import br.com.ejtc.services.EntregaService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {

	@Autowired
    private EntregaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Entrega>> findAll() {
        Collection<Entrega> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codEntrega}", method = RequestMethod.GET)
    public ResponseEntity<Entrega> find(@PathVariable Integer codEntrega) {
        Entrega obj = service.findById(codEntrega);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Entrega> insert(@Valid @RequestBody Entrega obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codEntrega}", method = RequestMethod.PUT)
    public ResponseEntity<Entrega> update(@Valid @RequestBody Entrega obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codEntrega}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codEntrega) {
        service.delete(codEntrega);
        return ResponseEntity.noContent().build();
    }
}
