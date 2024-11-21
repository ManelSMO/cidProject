package br.edu.unoesc.CID.service;


import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.repository.PessoaEnvolvidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PessoaEnvolvidaService {

    @Autowired
    private PessoaEnvolvidaRepository pessoaEnvolvidaRepository;

    public PessoaEnvolvida adicionarPessoaEnvolvida(PessoaEnvolvida pessoaEnvolvida) {
        return pessoaEnvolvidaRepository.save(pessoaEnvolvida);
    }

    public List<PessoaEnvolvida> listarPessoasEnvolvidasPorOcorrencia(Long idOcorrencia) {
        return pessoaEnvolvidaRepository.findByOcorrenciaIdOcorrencia(idOcorrencia);
    }

}
