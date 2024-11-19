package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEnvolvimentoRepository extends JpaRepository<TipoEnvolvimento, Integer> {

}
