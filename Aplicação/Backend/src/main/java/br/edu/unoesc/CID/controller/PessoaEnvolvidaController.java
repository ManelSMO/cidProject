package br.edu.unoesc.CID.controller;


import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.service.PessoaEnvolvidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas-envolvidas")
public class PessoaEnvolvidaController {

    @Autowired
    private PessoaEnvolvidaService pessoaEnvolvidaService;

    @PostMapping("/adicionar")
    public ResponseEntity<PessoaEnvolvida> adicionarPessoaEnvolvida(@Valid @RequestBody PessoaEnvolvida pessoaEnvolvida) {
        PessoaEnvolvida novaPessoaEnvolvida = pessoaEnvolvidaService.adicionarPessoaEnvolvida(pessoaEnvolvida);
        return ResponseEntity.ok(novaPessoaEnvolvida);
    }

    @GetMapping("/ocorrencia/{idOcorrencia}")
    public ResponseEntity<List<PessoaEnvolvida>> listarPessoasEnvolvidasPorOcorrencia(@PathVariable Integer idOcorrencia) {
        List<PessoaEnvolvida> pessoas = pessoaEnvolvidaService.listarPessoasEnvolvidasPorOcorrencia(idOcorrencia);
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/ocorrencias/{id}/pessoas-envolvidas")
    public ResponseEntity<List<PessoaEnvolvida>> listarPessoasEnvolvidas(@PathVariable Integer id) {
        List<PessoaEnvolvida> pessoasEnvolvidas = pessoaEnvolvidaService.listarPessoasEnvolvidasPorOcorrencia(id);
        return ResponseEntity.ok(pessoasEnvolvidas);
    }

}

