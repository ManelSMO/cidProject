package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Estado;
import br.edu.unoesc.CID.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas a estados.
 * Exponibiliza endpoints para cadastrar e listar estados.
 */
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    /**
     * Endpoint para cadastrar um novo estado.
     *
     * @param estado o estado a ser cadastrado, deve ser passado no corpo da requisição.
     * @return o estado cadastrado.
     */
    @PostMapping("/cadastrar")
    public Estado cadastrarEstado(@RequestBody Estado estado) {
        return estadoService.cadastrarEstado(estado);
    }

    /**
     * Endpoint para listar todos os estados.
     *
     * @return uma lista de todos os estados cadastrados.
     */
    @GetMapping
    public List<Estado> listarEstados() {
        return estadoService.listarEstados();
    }
}
