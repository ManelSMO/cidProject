package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de funcionários.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link Funcionario}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir funcionários.
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
