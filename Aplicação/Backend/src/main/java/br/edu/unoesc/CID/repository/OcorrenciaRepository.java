package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    Optional<Ocorrencia> findById(Long id);
}
