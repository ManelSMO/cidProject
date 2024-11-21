package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Estado;
import br.edu.unoesc.CID.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/cadastrar")
    public Estado cadastrarEstado(@RequestBody Estado estado) {
        return estadoService.cadastrarEstado(estado);
    }

    @GetMapping
    public List<Estado> listarEstados() {
        return estadoService.listarEstados();
    }
}

