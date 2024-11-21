package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.AcessoPolicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcessoPolicialRepository extends JpaRepository<AcessoPolicial, Long> {

    // Verifica se a matrícula já existe
    boolean existsByMatricula(String matricula);

    // Busca um AcessoPolicial pela matrícula
    Optional<AcessoPolicial> findByMatricula(String matricula);
}
