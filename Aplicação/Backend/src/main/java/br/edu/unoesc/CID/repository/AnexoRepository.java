package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Anexos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnexoRepository extends JpaRepository<Anexos, Integer> {
    List<Anexos> findByOcorrenciaId(Integer idOcorrencia);
}
