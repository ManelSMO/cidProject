package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    //Metodo para registro de nova ocorrencia
    public Ocorrencia novaOcorrencia(Ocorrencia ocorrencia) {

        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia consultarOcorrencia(Long id) {
        Optional<Ocorrencia> ocorrencia = ocorrenciaRepository.findById(id);

        if (!ocorrencia.isPresent()) {
            throw new IllegalArgumentException("Ocorrência não registrada");
        }
        return ocorrencia.get();
    }
}
