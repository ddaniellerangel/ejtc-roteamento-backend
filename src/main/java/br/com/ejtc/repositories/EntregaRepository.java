package br.com.ejtc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ejtc.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}
