package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.TipoEnvolvimento;
import br.edu.unoesc.CID.repository.TipoEnvolvimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEnvolvimentoService {
    @Autowired
    private TipoEnvolvimentoRepository tipoEnvolvimentoRepository;

    public TipoEnvolvimento cadastrarTipoEnvolvimento(TipoEnvolvimento tipoEnvolvimento) {
        return tipoEnvolvimentoRepository.save(tipoEnvolvimento);
    }

    public List<TipoEnvolvimento> listarTiposEnvolvimento() {

        return tipoEnvolvimentoRepository.findAll();
    }
}

