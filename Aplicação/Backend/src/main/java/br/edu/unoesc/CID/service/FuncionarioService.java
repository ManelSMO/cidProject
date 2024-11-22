package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Funcionario;
import br.edu.unoesc.CID.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas aos funcionários.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    /**
     * Cadastra um novo funcionário.
     *
     * @param funcionario o funcionário a ser cadastrado.
     * @return o funcionário salvo.
     */
    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    /**
     * Lista todos os funcionários cadastrados.
     *
     * @return uma lista de funcionários.
     */
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    /**
     * Busca um funcionário pelo ID.
     *
     * @param id o ID do funcionário a ser buscado.
     * @return o funcionário encontrado.
     * @throws RuntimeException se o funcionário não for encontrado.
     */
    public Funcionario buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }
}
