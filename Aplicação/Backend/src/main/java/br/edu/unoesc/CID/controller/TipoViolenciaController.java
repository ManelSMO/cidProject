package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoViolencia;
import br.edu.unoesc.CID.service.TipoViolenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão dos tipos de violência.
 * Fornece endpoints para cadastrar e listar os tipos de violência.
 */
@RestController
@RequestMapping("/tipos-violencia")
public class TipoViolenciaController {

    @Autowired
    private TipoViolenciaService tipoViolenciaService;

    /**
     * Endpoint para cadastrar um novo tipo de violência.
     *
     * @param tipoViolencia o tipo de violência a ser cadastrado.
     * @return o tipo de violência cadastrado.
     */
    @PostMapping("/cadastrar")
    public TipoViolencia cadastrarTipoViolencia(@RequestBody TipoViolencia tipoViolencia) {
        return tipoViolenciaService.cadastrarTipoViolencia(tipoViolencia);
    }

    /**
     * Endpoint para listar todos os tipos de violência cadastrados.
     *
     * @return a lista de tipos de violência.
     */
    @GetMapping
    public List<TipoViolencia> listarTiposViolencia() {
        return tipoViolenciaService.listarTiposViolencia();
    }
}
