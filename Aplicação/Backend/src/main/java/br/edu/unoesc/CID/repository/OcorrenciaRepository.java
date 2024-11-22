package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório de ocorrências.
 *
 * Interface de repositório para realizar operações sobre a entidade {@link Ocorrencia}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados, como salvar, buscar, atualizar e excluir ocorrências.
 * Além disso, contém consultas personalizadas para chamar procedures e consultas específicas.
 */
@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    /**
     * Busca uma ocorrência pelo seu ID.
     *
     * @param id o ID da ocorrência.
     * @return um {@link Optional} contendo a ocorrência, se encontrada.
     */
    Optional<Ocorrencia> findById(Long id);

    /**
     * Chama a procedure para inserir uma nova ocorrência no banco de dados.
     *
     * @param descoco a descrição da ocorrência.
     * @param datoco a data da ocorrência.
     * @param lococo o local da ocorrência.
     * @param staoco o status da ocorrência.
     * @param cidadeId o ID da cidade associada à ocorrência.
     * @param funcionarioId o ID do funcionário que registrou a ocorrência.
     * @param tipoViolencia o tipo de violência associado à ocorrência.
     * @param tipoOcorrencia o ID do tipo de ocorrência.
     */
    @Query(value = "CALL inserir_ocorrencia(:descoco, :datoco, :lococo, :staoco, :cidadeidcid, :funcionariopessoaidpes, :tipo_violenciaidtipooco, :tipo_ocorrenciaidtipoco)", nativeQuery = true)
    void inserirOcorrencia(
            @Param("descoco") String descoco,
            @Param("datoco") String datoco,
            @Param("lococo") String lococo,
            @Param("staoco") String staoco,
            @Param("cidadeidcid") int cidadeId,
            @Param("funcionariopessoaidpes") int funcionarioId,
            @Param("tipo_violenciaidtipooco") String tipoViolencia,
            @Param("tipo_ocorrenciaidtipoco") int tipoOcorrencia
    );

    /**
     * Chama a procedure para atualizar o status de uma ocorrência.
     *
     * @param idoco o ID da ocorrência.
     * @param novoStatus o novo status da ocorrência.
     */
    @Query(value = "CALL atualizar_status_ocorrencia(:idoco, :novoStaoco)", nativeQuery = true)
    void atualizarStatusOcorrencia(
            @Param("idoco") int idoco,
            @Param("novoStaoco") String novoStatus
    );

    /**
     * Consulta todas as ocorrências validadas.
     *
     * @return uma lista de objetos contendo as ocorrências validadas.
     */
    @Query(value = "SELECT * FROM listar_ocorrencias_validadas()", nativeQuery = true)
    List<Object[]> listarOcorrenciasValidadas();

    /**
     * Consulta o histórico de ocorrências de um cidadão com base no CPF.
     *
     * @param cpf o CPF do cidadão.
     * @return uma lista de objetos contendo o histórico de ocorrências.
     */
    @Query(value = "SELECT * FROM consultar_historico_ocorrencias(:cpf)", nativeQuery = true)
    List<Object[]> consultarHistoricoOcorrencias(@Param("cpf") String cpf);

    /**
     * Verifica se o CPF existe no banco de dados.
     *
     * @param cpf o CPF a ser verificado.
     * @return true se o CPF existir, false caso contrário.
     */
    @Query(value = "SELECT verificar_cpf_existente(:cpf)", nativeQuery = true)
    Boolean verificarCpfExistente(@Param("cpf") String cpf);
}
