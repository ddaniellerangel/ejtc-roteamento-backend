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

import br.com.ejtc.model.Estado;
import br.com.ejtc.services.EstadoService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
    private EstadoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Estado>> findAll() {
        Collection<Estado> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codEstado}", method = RequestMethod.GET)
    public ResponseEntity<Estado> find(@PathVariable Integer codEstado) {
        Estado obj = service.findById(codEstado);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Estado> insert(@Valid @RequestBody Estado obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codEstado}", method = RequestMethod.PUT)
    public ResponseEntity<Estado> update(@Valid @RequestBody Estado obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codEstado}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codEstado) {
        service.delete(codEstado);
        return ResponseEntity.noContent().build();
    }
}
