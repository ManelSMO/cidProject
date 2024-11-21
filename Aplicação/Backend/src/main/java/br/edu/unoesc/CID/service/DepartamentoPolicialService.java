package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import br.edu.unoesc.CID.repository.DepartamentoPolicialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
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

