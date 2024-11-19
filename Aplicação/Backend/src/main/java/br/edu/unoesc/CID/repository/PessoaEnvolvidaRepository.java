package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaEnvolvidaRepository extends JpaRepository<PessoaEnvolvida, Integer> {
    List<PessoaEnvolvida> findByOcorrenciaId(Integer idOcorrencia);
}
