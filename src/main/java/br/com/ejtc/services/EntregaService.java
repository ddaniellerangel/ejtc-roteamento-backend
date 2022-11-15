package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Entrega;
import br.com.ejtc.repositories.EntregaRepository;

@Service
public class EntregaService {
	
	@Autowired
    private EntregaRepository repository;
	
	public Entrega findById(final Integer codEntrega) {
        try {
        	Entrega obj = repository.findById(codEntrega).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codEntrega + Entrega.class.getName(), null);
        }
    }    

    public Collection<Entrega> findAll() {
        return repository.findAll();
    }

    public Entrega insert(final Entrega obj) {
    	obj.setCodEntrega(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Entrega não foi(foram) preenchido(s)");
        }
    }

    public Entrega update(final Entrega obj) {
    	findById(obj.getCodEntrega());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Entrega não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codEntrega) {
        findById(codEntrega);
        try {
            repository.deleteById(codEntrega);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír Entrega!");
        }
    }
}
