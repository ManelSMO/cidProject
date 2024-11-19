package br.edu.unoesc.CID.service;


import br.edu.unoesc.CID.entity.Anexos;
import br.edu.unoesc.CID.repository.AnexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnexosService {
    @Autowired
    private AnexoRepository anexoRepository;

    public Anexos adicionarAnexo(Anexos anexo) {
        return anexoRepository.save(anexo);
    }

    public List<Anexos> listarAnexosPorOcorrencia(Integer idOcorrencia) {
        return anexoRepository.findByOcorrenciaId(idOcorrencia);
    }
}

