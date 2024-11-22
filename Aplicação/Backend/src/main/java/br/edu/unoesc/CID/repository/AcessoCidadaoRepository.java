package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.AcessoCidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório de acesso dos cidadãos à aplicação.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link AcessoCidadao}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados.
 */
@Repository
public interface AcessoCidadaoRepository extends JpaRepository<AcessoCidadao, Long> {

    /**
     * Verifica se o CPF do usuário já está cadastrado na base de dados.
     *
     * @param cpfUsuario o CPF do usuário a ser verificado.
     * @return {@code true} se o CPF estiver cadastrado, caso contrário {@code false}.
     */
    boolean existsByCpfUsuario(String cpfUsuario);

    /**
     * Busca um usuário pelo CPF.
     *
     * @param cpfUsuario o CPF do usuário a ser buscado.
     * @return um {@link Optional} contendo o {@link AcessoCidadao} correspondente, se encontrado, ou vazio caso contrário.
     */
    Optional<AcessoCidadao> findByCpfUsuario(String cpfUsuario);
}
