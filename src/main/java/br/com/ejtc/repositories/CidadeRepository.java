package br.com.ejtc.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ejtc.model.Cidade;
import br.com.ejtc.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Transactional(readOnly = true)
    public Collection<Cidade> findByEstado(Estado estado);
}
