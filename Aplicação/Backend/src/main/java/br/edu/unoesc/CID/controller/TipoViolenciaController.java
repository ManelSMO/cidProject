package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoViolencia;
import br.edu.unoesc.CID.service.TipoViolenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-violencia")
public class TipoViolenciaController {

    @Autowired
    private TipoViolenciaService tipoViolenciaService;

    @PostMapping("/cadastrar")
    public TipoViolencia cadastrarTipoViolencia(@RequestBody TipoViolencia tipoViolencia) {
        return tipoViolenciaService.cadastrarTipoViolencia(tipoViolencia);
    }

    @GetMapping
    public List<TipoViolencia> listarTiposViolencia() {
        return tipoViolenciaService.listarTiposViolencia();
    }
}
