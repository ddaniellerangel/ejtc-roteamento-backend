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

import br.com.ejtc.model.Cliente;
import br.com.ejtc.services.ClienteService;
import br.com.ejtc.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
    private ClienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Cliente>> findAll() {
        Collection<Cliente> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{codCliente}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer codCliente) {
        Cliente obj = service.findById(codCliente);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente obj, BindingResult br) {
        if (br.hasErrors()) {
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		}
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{codCliente}", method = RequestMethod.PUT)
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{codCliente}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer codCliente) {
        service.delete(codCliente);
        return ResponseEntity.noContent().build();
    }
}
