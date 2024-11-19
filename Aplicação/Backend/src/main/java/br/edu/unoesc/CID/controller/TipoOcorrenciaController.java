package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import br.edu.unoesc.CID.service.TipoOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-ocorrencia")
public class TipoOcorrenciaController {
    @Autowired
    private TipoOcorrenciaService tipoOcorrenciaService;

    @PostMapping("/cadastrar")
    public TipoOcorrencia cadastrarTipoOcorrencia(@RequestBody TipoOcorrencia tipoOcorrencia) {
        return tipoOcorrenciaService.cadastrarTipoOcorrencia(tipoOcorrencia);
    }

    @GetMapping
    public List<TipoOcorrencia> listarTiposOcorrencia() {

        return tipoOcorrenciaService.listarTiposOcorrencia();
    }
}

