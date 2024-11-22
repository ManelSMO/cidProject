package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de TipoOcorrencia.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link TipoOcorrencia}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir tipos de ocorrência.
 */
@Repository
public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Integer> {
    // O Spring Data JPA fornecerá a implementação padrão de métodos como save(), findAll(), findById(), etc.
}
