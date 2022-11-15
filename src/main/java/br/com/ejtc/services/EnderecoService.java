package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Endereco;
import br.com.ejtc.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
    private EnderecoRepository repository;
	
	public Endereco findById(final Integer codEndereco) {
        try {
        	Endereco obj = repository.findById(codEndereco).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codEndereco + EnderecoService.class.getName(), null);
        }
    }    

    public Collection<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco insert(final Endereco obj) {
    	obj.setCodEndereco(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Endereco não foi(foram) preenchido(s)");
        }
    }

    public Endereco update(final Endereco obj) {
    	findById(obj.getCodEndereco());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Endereco não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codEndereco) {
        findById(codEndereco);
        try {
            repository.deleteById(codEndereco);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír Endereco!");
        }
    }
}
