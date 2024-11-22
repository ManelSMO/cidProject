package br.edu.unoesc.CID.repository;

import br.edu.unoesc.CID.entity.Anexos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de anexos relacionados a ocorrências.
 *
 * Interface de repositório para realizar operações CRUD sobre a entidade {@link Anexos}.
 * Estende {@link JpaRepository} para fornecer operações padrão de banco de dados.
 */
@Repository
public interface AnexoRepository extends JpaRepository<Anexos, Long> {

    /**
     * Busca os anexos associados a uma ocorrência específica, identificada pelo ID da ocorrência.
     *
     * @param idOcorrencia o ID da ocorrência cujos anexos serão recuperados.
     * @return uma lista de {@link Anexos} relacionados à ocorrência com o ID fornecido.
     */
    List<Anexos> findByOcorrenciaIdOcorrencia(Long idOcorrencia);
}
