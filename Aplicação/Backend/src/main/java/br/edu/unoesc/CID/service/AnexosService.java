package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Anexos;
import br.edu.unoesc.CID.repository.AnexoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas aos anexos.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AnexosService {

    @Autowired
    private AnexoRepository anexoRepository;

    /**
     * Adiciona um novo anexo.
     *
     * @param anexo o anexo a ser adicionado.
     * @return o anexo salvo.
     */
    public Anexos adicionarAnexo(Anexos anexo) {
        return anexoRepository.save(anexo);
    }

    /**
     * Lista os anexos associados a uma ocorrência específica.
     *
     * @param idOcorrencia o identificador da ocorrência.
     * @return uma lista de anexos relacionados à ocorrência.
     */
    public List<Anexos> listarAnexosPorOcorrencia(Long idOcorrencia) {
        return anexoRepository.findByOcorrenciaIdOcorrencia(idOcorrencia);
    }
}
