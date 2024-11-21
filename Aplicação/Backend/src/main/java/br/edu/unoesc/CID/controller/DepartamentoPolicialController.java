package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import br.edu.unoesc.CID.service.DepartamentoPolicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departamentos-policiais")
public class DepartamentoPolicialController {

    @Autowired
    private DepartamentoPolicialService departamentoPolicialService;

    @PostMapping("/cadastrar")
    public DepartamentoPolicial cadastrarDepartamentoPolicial(@RequestBody DepartamentoPolicial departamento) {
        return departamentoPolicialService.cadastrarDepartamentoPolicial(departamento);
    }

    @GetMapping
    public List<DepartamentoPolicial> listarDepartamentos() {
        return departamentoPolicialService.listarDepartamentos();
    }
}

