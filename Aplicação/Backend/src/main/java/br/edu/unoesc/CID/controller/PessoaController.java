package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Pessoa;
import br.edu.unoesc.CID.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das pessoas.
 * Fornece endpoints para cadastrar, listar e buscar pessoas.
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    /**
     * Endpoint para cadastrar uma nova pessoa.
     *
     * @param pessoa a pessoa a ser cadastrada.
     * @return uma resposta com o status da operação.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPessoa(@Valid @RequestBody Pessoa pessoa) {
        try {
            pessoaService.cadastrarPessoa(pessoa);
            return ResponseEntity.ok("Pessoa cadastrada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar todas as pessoas cadastradas.
     *
     * @return a lista de todas as pessoas.
     */
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    /**
     * Endpoint para buscar uma pessoa pelo seu ID.
     *
     * @param id o ID da pessoa a ser buscada.
     * @return a pessoa encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }
}
