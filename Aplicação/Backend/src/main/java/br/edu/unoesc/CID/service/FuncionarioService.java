package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Funcionario;
import br.edu.unoesc.CID.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {

        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {

        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Integer id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }
}
