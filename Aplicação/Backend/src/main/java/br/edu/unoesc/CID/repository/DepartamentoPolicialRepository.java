package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de departamentos policiais.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link DepartamentoPolicial}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir departamentos policiais.
 */
@Repository
public interface DepartamentoPolicialRepository extends JpaRepository<DepartamentoPolicial, Integer> {
}
