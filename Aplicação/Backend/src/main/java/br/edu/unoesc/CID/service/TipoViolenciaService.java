package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoViolencia;
import br.edu.unoesc.CID.repository.TipoViolenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar os tipos de violência.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class TipoViolenciaService {

    @Autowired
    private TipoViolenciaRepository tipoViolenciaRepository;

    /**
     * Cadastra um novo tipo de violência no sistema.
     *
     * @param tipoViolencia a entidade do tipo de violência a ser cadastrada.
     * @return o tipo de violência cadastrado.
     * @throws IllegalArgumentException caso a descrição do tipo de violência seja nula ou em branco.
     */
    public TipoViolencia cadastrarTipoViolencia(TipoViolencia tipoViolencia) {
        if (tipoViolencia.getDescTipoViolencia() == null || tipoViolencia.getDescTipoViolencia().isBlank()) {
            throw new IllegalArgumentException("A descrição do tipo de violência é obrigatória.");
        }
        return tipoViolenciaRepository.save(tipoViolencia);
    }

    /**
     * Lista todos os tipos de violência cadastrados no sistema.
     *
     * @return uma lista de tipos de violência.
     */
    public List<TipoViolencia> listarTiposViolencia() {
        return tipoViolenciaRepository.findAll();
    }
}
