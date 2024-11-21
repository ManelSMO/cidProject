package br.edu.unoesc.CID.service;


import br.edu.unoesc.CID.entity.Anexos;
import br.edu.unoesc.CID.repository.AnexoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnexosService {

    @Autowired
    private AnexoRepository anexoRepository;

    public Anexos adicionarAnexo(Anexos anexo) {
        return anexoRepository.save(anexo);
    }

    public List<Anexos> listarAnexosPorOcorrencia(Long idOcorrencia) {
        return anexoRepository.findByOcorrenciaIdOcorrencia(idOcorrencia);
    }
}

