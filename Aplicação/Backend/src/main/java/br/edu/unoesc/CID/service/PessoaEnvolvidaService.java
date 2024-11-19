package br.edu.unoesc.CID.service;


import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.repository.PessoaEnvolvidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaEnvolvidaService {

    @Autowired
    private PessoaEnvolvidaRepository pessoaEnvolvidaRepository;

    public PessoaEnvolvida adicionarPessoaEnvolvida(PessoaEnvolvida pessoaEnvolvida) {
        return pessoaEnvolvidaRepository.save(pessoaEnvolvida);
    }

    public List<PessoaEnvolvida> listarPessoasEnvolvidasPorOcorrencia(Integer idOcorrencia) {
        return pessoaEnvolvidaRepository.findByOcorrenciaId(idOcorrencia);
    }

}
