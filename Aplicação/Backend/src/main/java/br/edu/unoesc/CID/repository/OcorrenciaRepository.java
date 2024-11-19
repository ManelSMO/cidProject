package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    Optional<Ocorrencia> findById(Long id);

    // Chama a procedure para inserir uma ocorrência
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

    @Query(value = "CALL atualizar_status_ocorrencia(:idoco, :novoStaoco)", nativeQuery = true)
    void atualizarStatusOcorrencia(
            @Param("idoco") int idoco,
            @Param("novoStaoco") String novoStatus
    );

    @Query(value = "SELECT * FROM listar_ocorrencias_validadas()", nativeQuery = true)
    List<Object[]> listarOcorrenciasValidadas();

    @Query(value = "SELECT * FROM consultar_historico_ocorrencias(:cpf)", nativeQuery = true)
    List<Object[]> consultarHistoricoOcorrencias(@Param("cpf") String cpf);

    @Query(value = "SELECT verificar_cpf_existente(:cpf)", nativeQuery = true)
    Boolean verificarCpfExistente(@Param("cpf") String cpf);

}
