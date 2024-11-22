package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.DepartamentoPolicial;
import br.edu.unoesc.CID.repository.DepartamentoPolicialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas aos departamentos policiais.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DepartamentoPolicialService {

    @Autowired
    private DepartamentoPolicialRepository departamentoPolicialRepository;

    /**
     * Cadastra um novo departamento policial.
     *
     * @param departamento o departamento policial a ser cadastrado.
     * @return o departamento policial salvo.
     */
    public DepartamentoPolicial cadastrarDepartamentoPolicial(DepartamentoPolicial departamento) {
        return departamentoPolicialRepository.save(departamento);
    }

    /**
     * Lista todos os departamentos policiais cadastrados.
     *
     * @return uma lista de departamentos policiais.
     */
    public List<DepartamentoPolicial> listarDepartamentos() {
        return departamentoPolicialRepository.findAll();
    }
}
