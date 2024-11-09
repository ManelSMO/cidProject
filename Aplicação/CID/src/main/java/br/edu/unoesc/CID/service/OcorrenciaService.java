package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    public Ocorrencia novaOcorrencia(Ocorrencia ocorrencia) {
        return;
    }

    public Ocorrencia consultarOcorrencia() {
        return;
    }
}
