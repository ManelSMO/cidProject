package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import br.edu.unoesc.CID.service.TipoEnvolvimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão dos tipos de envolvimento.
 * Fornece endpoints para cadastrar e listar os tipos de envolvimento.
 */
@RestController
@RequestMapping("/tipos-envolvimento")
public class TipoEnvolvimentoController {

    @Autowired
    private TipoEnvolvimentoService tipoEnvolvimentoService;

    /**
     * Endpoint para cadastrar um novo tipo de envolvimento.
     *
     * @param tipoEnvolvimento o tipo de envolvimento a ser cadastrado.
     * @return o tipo de envolvimento cadastrado.
     */
    @PostMapping("/cadastrar")
    public TipoEnvolvimento cadastrarTipoEnvolvimento(@RequestBody TipoEnvolvimento tipoEnvolvimento) {
        return tipoEnvolvimentoService.cadastrarTipoEnvolvimento(tipoEnvolvimento);
    }

    /**
     * Endpoint para listar todos os tipos de envolvimento cadastrados.
     *
     * @return a lista de tipos de envolvimento.
     */
    @GetMapping
    public List<TipoEnvolvimento> listarTiposEnvolvimento() {
        return tipoEnvolvimentoService.listarTiposEnvolvimento();
    }
}
