package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoViolencia;
import br.edu.unoesc.CID.repository.TipoViolenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TipoViolenciaService {

    @Autowired
    private TipoViolenciaRepository tipoViolenciaRepository;

    // Cadastrar novo tipo de violência
    public TipoViolencia cadastrarTipoViolencia(TipoViolencia tipoViolencia) {
        if (tipoViolencia.getDescTipoViolencia() == null || tipoViolencia.getDescTipoViolencia().isBlank()) {
            throw new IllegalArgumentException("A descrição do tipo de violência é obrigatória.");
        }
        return tipoViolenciaRepository.save(tipoViolencia);
    }

    public List<TipoViolencia> listarTiposViolencia() {
        return tipoViolenciaRepository.findAll();
    }
}

