package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import br.edu.unoesc.CID.repository.TipoEnvolvimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar os tipos de envolvimento.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TipoEnvolvimentoService {

    @Autowired
    private TipoEnvolvimentoRepository tipoEnvolvimentoRepository;

    /**
     * Cadastra um novo tipo de envolvimento no sistema.
     *
     * @param tipoEnvolvimento a entidade do tipo de envolvimento a ser cadastrada.
     * @return o tipo de envolvimento cadastrado.
     */
    public TipoEnvolvimento cadastrarTipoEnvolvimento(TipoEnvolvimento tipoEnvolvimento) {
        return tipoEnvolvimentoRepository.save(tipoEnvolvimento);
    }

    /**
     * Lista todos os tipos de envolvimento cadastrados no sistema.
     *
     * @return uma lista de tipos de envolvimento.
     */
    public List<TipoEnvolvimento> listarTiposEnvolvimento() {
        return tipoEnvolvimentoRepository.findAll();
    }
}
