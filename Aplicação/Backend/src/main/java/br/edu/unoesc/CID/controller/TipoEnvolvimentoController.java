package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import br.edu.unoesc.CID.service.TipoEnvolvimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-envolvimento")
public class TipoEnvolvimentoController {
    @Autowired
    private TipoEnvolvimentoService tipoEnvolvimentoService;

    @PostMapping("/cadastrar")
    public TipoEnvolvimento cadastrarTipoEnvolvimento(@RequestBody TipoEnvolvimento tipoEnvolvimento) {
        return tipoEnvolvimentoService.cadastrarTipoEnvolvimento(tipoEnvolvimento);
    }

    @GetMapping
    public List<TipoEnvolvimento> listarTiposEnvolvimento() {
        return tipoEnvolvimentoService.listarTiposEnvolvimento();
    }
}

