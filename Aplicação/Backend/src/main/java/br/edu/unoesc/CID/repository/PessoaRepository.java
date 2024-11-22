package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de Pessoa.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link Pessoa}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir pessoas.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /**
     * Busca pessoas que contenham o nome fornecido.
     *
     * @param nomePessoa o nome (ou parte do nome) da pessoa a ser buscada.
     * @return uma lista de {@link Pessoa} cujo nome contém o valor fornecido.
     */
    List<Pessoa> findByNomePessoaContaining(String nomePessoa);
}
