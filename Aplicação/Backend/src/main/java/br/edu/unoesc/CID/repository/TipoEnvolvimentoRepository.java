package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de TipoEnvolvimento.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link TipoEnvolvimento}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir tipos de envolvimento.
 */
@Repository
public interface TipoEnvolvimentoRepository extends JpaRepository<TipoEnvolvimento, Integer> {
    // O Spring Data JPA fornecerá a implementação padrão de métodos como save(), findAll(), findById(), etc.
}
