package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoPolicialRepository extends JpaRepository<DepartamentoPolicial, Integer> {

}

