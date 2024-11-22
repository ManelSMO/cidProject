package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import br.edu.unoesc.CID.service.DepartamentoPolicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas a departamentos policiais.
 * Exponibiliza endpoints para cadastrar e listar departamentos policiais.
 */
@RestController
@RequestMapping("/departamentos-policiais")
public class DepartamentoPolicialController {

    @Autowired
    private DepartamentoPolicialService departamentoPolicialService;

    /**
     * Endpoint para cadastrar um novo departamento policial.
     *
     * @param departamento o departamento policial a ser cadastrado, deve ser passado no corpo da requisição.
     * @return o departamento policial cadastrado.
     */
    @PostMapping("/cadastrar")
    public DepartamentoPolicial cadastrarDepartamentoPolicial(@RequestBody DepartamentoPolicial departamento) {
        return departamentoPolicialService.cadastrarDepartamentoPolicial(departamento);
    }

    /**
     * Endpoint para listar todos os departamentos policiais.
     *
     * @return uma lista de todos os departamentos policiais cadastrados.
     */
    @GetMapping
    public List<DepartamentoPolicial> listarDepartamentos() {
        return departamentoPolicialService.listarDepartamentos();
    }
}
