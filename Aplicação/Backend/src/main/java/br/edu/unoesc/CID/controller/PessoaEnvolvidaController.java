package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.service.PessoaEnvolvidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das pessoas envolvidas em ocorrências.
 * Fornece endpoints para adicionar e listar pessoas envolvidas em uma ocorrência.
 */
@RestController
@RequestMapping("/pessoas-envolvidas")
public class PessoaEnvolvidaController {

    @Autowired
    private PessoaEnvolvidaService pessoaEnvolvidaService;

    /**
     * Endpoint para adicionar uma pessoa envolvida em uma ocorrência.
     *
     * @param pessoaEnvolvida a pessoa envolvida a ser adicionada.
     * @return uma resposta com o status da operação.
     */
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarPessoaEnvolvida(@Valid @RequestBody PessoaEnvolvida pessoaEnvolvida) {
        try {
            pessoaEnvolvidaService.adicionarPessoaEnvolvida(pessoaEnvolvida);
            return ResponseEntity.ok("Pessoa envolvida adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar as pessoas envolvidas em uma ocorrência específica.
     *
     * @param idOcorrencia o ID da ocorrência.
     * @return a lista de pessoas envolvidas na ocorrência.
     */
    @GetMapping("/ocorrencia/{idOcorrencia}")
    public ResponseEntity<List<PessoaEnvolvida>> listarPessoasEnvolvidasPorOcorrencia(@PathVariable Long idOcorrencia) {
        List<PessoaEnvolvida> pessoas = pessoaEnvolvidaService.listarPessoasEnvolvidasPorOcorrencia(idOcorrencia);
        return ResponseEntity.ok(pessoas);
    }

    /**
     * Endpoint para listar as pessoas envolvidas em uma ocorrência específica por ID.
     *
     * @param id o ID da ocorrência.
     * @return a lista de pessoas envolvidas.
     */
    @GetMapping("/ocorrencias/{id}/pessoas-envolvidas")
    public ResponseEntity<List<PessoaEnvolvida>> listarPessoasEnvolvidas(@PathVariable Long id) {
        List<PessoaEnvolvida> pessoasEnvolvidas = pessoaEnvolvidaService.listarPessoasEnvolvidasPorOcorrencia(id);
        return ResponseEntity.ok(pessoasEnvolvidas);
    }
}
