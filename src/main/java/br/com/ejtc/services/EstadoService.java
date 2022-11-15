package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Estado;
import br.com.ejtc.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
    private EstadoRepository repository;
	
	public Estado findById(final Integer codEstado) {
        try {
        	Estado obj = repository.findById(codEstado).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codEstado + Estado.class.getName(), null);
        }
    }    

    public Collection<Estado> findAll() {
        return repository.findAll();
    }

    public Estado insert(final Estado obj) {
    	obj.setCodEstado(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Estado não foi(foram) preenchido(s)");
        }
    }

    public Estado update(final Estado obj) {
    	findById(obj.getCodEstado());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Estado não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codEstado) {
        findById(codEstado);
        try {
            repository.deleteById(codEstado);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír Estado!");
        }
    }
}
