package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import br.edu.unoesc.CID.service.TipoOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão dos tipos de ocorrência.
 * Fornece endpoints para cadastrar e listar os tipos de ocorrência.
 */
@RestController
@RequestMapping("/tipos-ocorrencia")
public class TipoOcorrenciaController {

    @Autowired
    private TipoOcorrenciaService tipoOcorrenciaService;

    /**
     * Endpoint para cadastrar um novo tipo de ocorrência.
     *
     * @param tipoOcorrencia o tipo de ocorrência a ser cadastrado.
     * @return o tipo de ocorrência cadastrado.
     */
    @PostMapping("/cadastrar")
    public TipoOcorrencia cadastrarTipoOcorrencia(@RequestBody TipoOcorrencia tipoOcorrencia) {
        return tipoOcorrenciaService.cadastrarTipoOcorrencia(tipoOcorrencia);
    }

    /**
     * Endpoint para listar todos os tipos de ocorrência cadastrados.
     *
     * @return a lista de tipos de ocorrência.
     */
    @GetMapping
    public List<TipoOcorrencia> listarTiposOcorrencia() {
        return tipoOcorrenciaService.listarTiposOcorrencia();
    }
}
