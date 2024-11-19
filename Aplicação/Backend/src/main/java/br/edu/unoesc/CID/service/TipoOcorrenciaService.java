package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoOcorrencia;
import br.edu.unoesc.CID.repository.TipoOcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoOcorrenciaService {

    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    public TipoOcorrencia cadastrarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        return tipoOcorrenciaRepository.save(tipoOcorrencia);
    }

    public List<TipoOcorrencia> listarTiposOcorrencia() {
        return tipoOcorrenciaRepository.findAll();
    }
}
