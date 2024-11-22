package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.AcessoPolicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório de acesso dos policiais à aplicação.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link AcessoPolicial}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados.
 */
@Repository
public interface AcessoPolicialRepository extends JpaRepository<AcessoPolicial, Long> {

    /**
     * Verifica se a matrícula do policial já está cadastrada na base de dados.
     *
     * @param matricula a matrícula do policial a ser verificada.
     * @return {@code true} se a matrícula estiver cadastrada, caso contrário {@code false}.
     */
    boolean existsByMatricula(String matricula);

    /**
     * Busca um acesso policial pela matrícula.
     *
     * @param matricula a matrícula do policial a ser buscada.
     * @return um {@link Optional} contendo o {@link AcessoPolicial} correspondente, se encontrado, ou vazio caso contrário.
     */
    Optional<AcessoPolicial> findByMatricula(String matricula);
}
