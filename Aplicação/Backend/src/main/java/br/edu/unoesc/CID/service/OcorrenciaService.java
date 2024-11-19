package br.edu.unoesc.CID.service;


import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OcorrenciaService {

    @Autowired
    private final OcorrenciaRepository ocorrenciaRepository;

    private static final Logger logger = LoggerFactory.getLogger(OcorrenciaService.class);

    //Metodo para registro de nova ocorrencia
    public Ocorrencia novaOcorrencia(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia consultarOcorrencia(Long id, Usuario usuario) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada"));

        if (usuario.getTipoUsuario() == TipoUsuario.CIVIL &&
                !usuario.getCpfPessoa().equals(ocorrencia.getCpfCivil())) {
            throw new SecurityException("Acesso negado à ocorrência.");
        }

        return ocorrencia;
    }

    public void inserirOcorrencia(String descoco, String datoco, String lococo, String staoco, int cidadeId, int funcionarioId, String tipoViolencia, int tipoOcorrencia) {
        ocorrenciaRepository.inserirOcorrencia(descoco, datoco, lococo, staoco, cidadeId, funcionarioId, tipoViolencia, tipoOcorrencia);
    }

    public void atualizarStatusOcorrencia(int idoco, String novoStatus, Usuario usuario) {
        ocorrenciaRepository.atualizarStatusOcorrencia(idoco, novoStatus);
    }

    public List<Object[]> listarOcorrenciasValidadas() {

        return ocorrenciaRepository.listarOcorrenciasValidadas();
    }

    public List<Object[]> consultarHistoricoOcorrencias(String cpf) {
        return ocorrenciaRepository.consultarHistoricoOcorrencias(cpf);
    }

    public Boolean verificarCpfExistente(String cpf) {

        return ocorrenciaRepository.verificarCpfExistente(cpf);
    }

    public List<Ocorrencia> listarOcorrencias() {
        return ocorrenciaRepository.findAll();
    }

}
