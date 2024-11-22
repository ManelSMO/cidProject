package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de Pessoa Envolvida.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link PessoaEnvolvida}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir pessoas envolvidas em ocorrências.
 */
@Repository
public interface PessoaEnvolvidaRepository extends JpaRepository<PessoaEnvolvida, Long> {

    /**
     * Busca todas as pessoas envolvidas em uma ocorrência específica.
     *
     * @param idOcorrencia o ID da ocorrência.
     * @return uma lista de {@link PessoaEnvolvida} associadas à ocorrência com o ID fornecido.
     */
    List<PessoaEnvolvida> findByOcorrenciaIdOcorrencia(Long idOcorrencia); // Corrigido para idOcorrencia
}
