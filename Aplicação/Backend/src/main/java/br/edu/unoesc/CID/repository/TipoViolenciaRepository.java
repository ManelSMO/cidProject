package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoViolencia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de TipoViolencia.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link TipoViolencia}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir tipos de violência.
 */
public interface TipoViolenciaRepository extends JpaRepository<TipoViolencia, String> {
    // O Spring Data JPA fornecerá a implementação padrão de métodos como save(), findAll(), findById(), etc.
}
