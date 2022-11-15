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

import br.com.ejtc.model.Rota;
import br.com.ejtc.services.RotaService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/rotas")
public class RotaController {

	@Autowired
    private RotaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Rota>> findAll() {
        Collection<Rota> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codRota}", method = RequestMethod.GET)
    public ResponseEntity<Rota> find(@PathVariable Integer codRota) {
        Rota obj = service.findById(codRota);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Rota> insert(@Valid @RequestBody Rota obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codRota}", method = RequestMethod.PUT)
    public ResponseEntity<Rota> update(@Valid @RequestBody Rota obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codRota}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codRota) {
        service.delete(codRota);
        return ResponseEntity.noContent().build();
    }
}
