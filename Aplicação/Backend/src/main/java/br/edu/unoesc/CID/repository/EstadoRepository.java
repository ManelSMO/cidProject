package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de estados.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link Estado}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir estados.
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
