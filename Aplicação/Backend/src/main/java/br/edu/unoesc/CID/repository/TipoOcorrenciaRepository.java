package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Integer> {

}
