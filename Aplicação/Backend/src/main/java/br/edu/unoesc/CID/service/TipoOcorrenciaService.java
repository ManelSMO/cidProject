package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import br.edu.unoesc.CID.repository.TipoOcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar os tipos de ocorrência.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TipoOcorrenciaService {

    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    /**
     * Cadastra um novo tipo de ocorrência no sistema.
     *
     * @param tipoOcorrencia a entidade do tipo de ocorrência a ser cadastrada.
     * @return o tipo de ocorrência cadastrado.
     */
    public TipoOcorrencia cadastrarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        return tipoOcorrenciaRepository.save(tipoOcorrencia);
    }

    /**
     * Lista todos os tipos de ocorrência cadastrados no sistema.
     *
     * @return uma lista de tipos de ocorrência.
     */
    public List<TipoOcorrencia> listarTiposOcorrencia() {
        return tipoOcorrenciaRepository.findAll();
    }
}
