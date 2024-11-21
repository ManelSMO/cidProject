package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Pessoa;
import br.edu.unoesc.CID.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa) {
        try {
            pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }
}
