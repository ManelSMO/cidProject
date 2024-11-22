package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Estado;
import br.edu.unoesc.CID.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas aos estados.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    /**
     * Cadastra um novo estado.
     *
     * @param estado o estado a ser cadastrado.
     * @return o estado salvo.
     * @throws IllegalArgumentException se o nome ou a sigla do estado não forem fornecidos.
     */
    public Estado cadastrarEstado(Estado estado) {
        if (estado.getNomeEstado() == null || estado.getSiglaEstado() == null) {
            throw new IllegalArgumentException("Nome e sigla do estado são obrigatórios.");
        }
        return estadoRepository.save(estado);
    }

    /**
     * Lista todos os estados cadastrados.
     *
     * @return uma lista de estados.
     */
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }
}
