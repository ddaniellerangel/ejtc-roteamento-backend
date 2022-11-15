package br.com.ejtc.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.ejtc.model.Cidade;
import br.com.ejtc.model.Estado;
import br.com.ejtc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
    private CidadeRepository repository;
	
	public Cidade findById(final Integer codCidade) {
        try {
        	Cidade obj = repository.findById(codCidade).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + codCidade + Cidade.class.getName(), null);
        }
    }    

    public Collection<Cidade> findAll() {
        return repository.findAll();
    }

    public Cidade insert(final Cidade obj) {
    	obj.setCodCidade(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Cidade não foi(foram) preenchido(s)");
        }
    }

    public Cidade update(final Cidade obj) {
    	findById(obj.getCodCidade());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) de Cidade não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer codCidade) {
        findById(codCidade);
        try {
            repository.deleteById(codCidade);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Erro ao excluír cidade!");
        }
    }
    
    public Collection<Cidade> findByEstado(Estado estado) {
        return repository.findByEstado(estado);
    }
}
