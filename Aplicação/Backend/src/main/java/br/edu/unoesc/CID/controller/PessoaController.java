package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Pessoa;
import br.edu.unoesc.CID.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastrar")
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {

        return pessoaService.cadastrarPessoa(pessoa);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {

        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPessoaPorId(@PathVariable Integer id) {

        return pessoaService.buscarPessoaPorId(id);
    }
}
