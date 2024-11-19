package br.edu.unoesc.CID.controller;


import br.edu.unoesc.CID.entity.Anexos;
import br.edu.unoesc.CID.service.AnexosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/anexos")
public class AnexosController {

    @Autowired
    private AnexosService anexoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Anexos> adicionarAnexo(@Valid @RequestBody Anexos anexo) {
        Anexos novoAnexo = anexoService.adicionarAnexo(anexo);
        return ResponseEntity.ok(novoAnexo);
    }

    @GetMapping("/ocorrencia/{idOcorrencia}")
    public ResponseEntity<List<Anexos>> listarAnexosPorOcorrencia(@PathVariable Integer idOcorrencia) {
        List<Anexos> anexos = anexoService.listarAnexosPorOcorrencia(idOcorrencia);
        return ResponseEntity.ok(anexos);
    }
}

