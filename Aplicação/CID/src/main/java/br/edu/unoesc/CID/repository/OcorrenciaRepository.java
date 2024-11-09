package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Integer> {

}
