package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.repository.PessoaEnvolvidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas às pessoas envolvidas em ocorrências.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PessoaEnvolvidaService {

    @Autowired
    private PessoaEnvolvidaRepository pessoaEnvolvidaRepository;

    /**
     * Adiciona uma pessoa envolvida a uma ocorrência.
     *
     * @param pessoaEnvolvida a entidade da pessoa envolvida a ser adicionada.
     * @return a pessoa envolvida adicionada.
     */
    public PessoaEnvolvida adicionarPessoaEnvolvida(PessoaEnvolvida pessoaEnvolvida) {
        return pessoaEnvolvidaRepository.save(pessoaEnvolvida);
    }

    /**
     * Lista todas as pessoas envolvidas em uma ocorrência específica.
     *
     * @param idOcorrencia o ID da ocorrência.
     * @return uma lista de pessoas envolvidas associadas à ocorrência.
     */
    public List<PessoaEnvolvida> listarPessoasEnvolvidasPorOcorrencia(Long idOcorrencia) {
        return pessoaEnvolvidaRepository.findByOcorrenciaIdOcorrencia(idOcorrencia);
    }
}
