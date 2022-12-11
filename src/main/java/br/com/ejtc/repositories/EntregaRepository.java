package br.com.ejtc.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ejtc.model.Entrega;
import br.com.ejtc.model.Rota;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

	@Transactional(readOnly = true)
    public Collection<Entrega> findByRota(Rota rota);
}
