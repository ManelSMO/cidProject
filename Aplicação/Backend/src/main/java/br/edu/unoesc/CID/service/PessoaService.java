package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Pessoa;
import br.edu.unoesc.CID.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa cadastrarPessoa(Pessoa pessoa) {

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {

        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Integer id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }
}
