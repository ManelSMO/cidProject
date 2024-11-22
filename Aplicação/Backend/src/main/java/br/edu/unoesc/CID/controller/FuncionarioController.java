package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Funcionario;
import br.edu.unoesc.CID.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelas operações relacionadas aos funcionários.
 * Oferece endpoints para cadastrar, listar e buscar funcionários.
 */
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    /**
     * Endpoint para cadastrar um novo funcionário.
     *
     * @param funcionario o funcionário a ser cadastrado.
     * @return o funcionário cadastrado.
     */
    @PostMapping("/cadastrar")
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.cadastrarFuncionario(funcionario);
    }

    /**
     * Endpoint para listar todos os funcionários cadastrados.
     *
     * @return uma lista de todos os funcionários.
     */
    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    /**
     * Endpoint para buscar um funcionário pelo seu identificador.
     *
     * @param id o id do funcionário a ser buscado.
     * @return o funcionário com o id especificado.
     */
    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }
}
