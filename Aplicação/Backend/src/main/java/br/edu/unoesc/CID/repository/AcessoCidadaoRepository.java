package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.AcessoCidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcessoCidadaoRepository extends JpaRepository<AcessoCidadao, Long> {

    // Verifica se o CPF já está cadastrado
    boolean existsByCpfUsuario(String cpfUsuario);

    // Busca pelo CPF do usuário
    Optional<AcessoCidadao> findByCpfUsuario(String cpfUsuario);
}
