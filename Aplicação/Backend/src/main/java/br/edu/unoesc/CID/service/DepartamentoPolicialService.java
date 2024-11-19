package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import br.edu.unoesc.CID.repository.DepartamentoPolicialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoPolicialService {

    @Autowired
    private DepartamentoPolicialRepository departamentoPolicialRepository;

    public DepartamentoPolicial cadastrarDepartamentoPolicial(DepartamentoPolicial departamento) {
        return departamentoPolicialRepository.save(departamento);
    }

    public List<DepartamentoPolicial> listarDepartamentos() {
        return departamentoPolicialRepository.findAll();
    }
}

