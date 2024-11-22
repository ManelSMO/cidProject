package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de cidades.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link Cidade}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir cidades.
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
