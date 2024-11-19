package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Funcionario;
import br.edu.unoesc.CID.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public Funcionario cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.cadastrarFuncionario(funcionario);
    }

    @GetMapping
    public List<Funcionario> listarFuncionarios() {

        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioPorId(@PathVariable Integer id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }
}
