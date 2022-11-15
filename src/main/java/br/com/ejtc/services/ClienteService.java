package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Cliente;
import br.com.ejtc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
    private ClienteRepository repository;
	
	public Cliente findById(final Integer codCliente) {
        try {
        	Cliente obj = repository.findById(codCliente).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codCliente + Cliente.class.getName(), null);
        }
    }    

    public Collection<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente insert(final Cliente obj) {
    	obj.setCodCliente(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Cliente não foi(foram) preenchido(s)");
        }
    }

    public Cliente update(final Cliente obj) {
    	findById(obj.getCodCliente());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Cliente não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codCliente) {
        findById(codCliente);
        try {
            repository.deleteById(codCliente);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír Cliente!");
        }
    }
}
