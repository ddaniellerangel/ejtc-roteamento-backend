package br.com.ejtc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ejtc.model.Rota;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Integer> {

}
