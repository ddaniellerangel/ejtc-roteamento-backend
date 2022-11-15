package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Rota;
import br.com.ejtc.repositories.RotaRepository;

@Service
public class RotaService {

	@Autowired
    private RotaRepository repository;
	
	public Rota findById(final Integer codRota) {
        try {
        	Rota obj = repository.findById(codRota).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codRota + Rota.class.getName(), null);
        }
    }    

    public Collection<Rota> findAll() {
        return repository.findAll();
    }

    public Rota insert(final Rota obj) {
    	obj.setCodRota(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Rota não foi(foram) preenchido(s)");
        }
    }

    public Rota update(final Rota obj) {
    	findById(obj.getCodRota());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Rota não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codRota) {
        findById(codRota);
        try {
            repository.deleteById(codRota);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír Rota!");
        }
    }
}
