package br.com.ejtc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ejtc.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
