package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Estado;
import br.edu.unoesc.CID.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // Cadastrar novo estado
    public Estado cadastrarEstado(Estado estado) {
        if (estado.getNomeEstado() == null || estado.getSiglaEstado() == null) {
            throw new IllegalArgumentException("Nome e sigla do estado são obrigatórios.");
        }
        return estadoRepository.save(estado);
    }

    // Listar todos os estados
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }
}

