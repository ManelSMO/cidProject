package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.OcorrenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas às ocorrências.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OcorrenciaService {

    @Autowired
    private final OcorrenciaRepository ocorrenciaRepository;

    /**
     * Registra uma nova ocorrência.
     *
     * @param ocorrencia a ocorrência a ser registrada.
     * @param usuario    o usuário responsável pelo registro.
     * @return a ocorrência registrada.
     */
    public Ocorrencia novaOcorrencia(Ocorrencia ocorrencia, Usuario usuario) {
        // Associa o usuário à ocorrência, caso seja um usuário civil.
        if (usuario.getTipoUsuario() == TipoUsuario.CIVIL) {
            ocorrencia.setCpfCivil(usuario.getCpfPessoa());
        }

        // Salva e retorna a ocorrência registrada.
        return ocorrenciaRepository.save(ocorrencia);
    }

    /**
     * Consulta uma ocorrência pelo ID, verificando permissões de acesso.
     *
     * @param id      o ID da ocorrência.
     * @param usuario o usuário que está consultando.
     * @return a ocorrência consultada.
     * @throws IllegalArgumentException se a ocorrência não for encontrada.
     * @throws SecurityException        se o usuário não tiver permissão para acessar a ocorrência.
     */
    public Ocorrencia consultarOcorrencia(Long id, Usuario usuario) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada"));

        // Verifica se um usuário civil pode acessar a ocorrência.
        if (usuario.getTipoUsuario() == TipoUsuario.CIVIL &&
                !usuario.getCpfPessoa().equals(ocorrencia.getCpfCivil())) {
            throw new SecurityException("Acesso negado à ocorrência.");
        }

        return ocorrencia;
    }

    /**
     * Insere uma ocorrência no banco de dados.
     *
     * @param descoco       descrição da ocorrência.
     * @param datoco        data da ocorrência.
     * @param lococo        local da ocorrência.
     * @param staoco        status da ocorrência.
     * @param cidadeId      ID da cidade.
     * @param funcionarioId ID do funcionário.
     * @param tipoViolencia tipo de violência.
     * @param tipoOcorrencia tipo de ocorrência.
     */
    public void inserirOcorrencia(String descoco, String datoco, String lococo, String staoco, int cidadeId,
                                  int funcionarioId, String tipoViolencia, int tipoOcorrencia) {
        ocorrenciaRepository.inserirOcorrencia(descoco, datoco, lococo, staoco, cidadeId, funcionarioId, tipoViolencia, tipoOcorrencia);
    }

    /**
     * Atualiza o status de uma ocorrência.
     *
     * @param idoco      o ID da ocorrência.
     * @param novoStatus o novo status a ser definido.
     * @param usuario    o usuário que está realizando a atualização.
     */
    public void atualizarStatusOcorrencia(int idoco, String novoStatus, Usuario usuario) {
        ocorrenciaRepository.atualizarStatusOcorrencia(idoco, novoStatus);
    }

    /**
     * Lista todas as ocorrências validadas.
     *
     * @return uma lista de ocorrências validadas.
     */
    public List<Object[]> listarOcorrenciasValidadas() {
        return ocorrenciaRepository.listarOcorrenciasValidadas();
    }

    /**
     * Consulta o histórico de ocorrências associado a um CPF.
     *
     * @param cpf o CPF do usuário.
     * @return uma lista de ocorrências no histórico.
     */
    public List<Object[]> consultarHistoricoOcorrencias(String cpf) {
        return ocorrenciaRepository.consultarHistoricoOcorrencias(cpf);
    }

    /**
     * Verifica se um CPF já está registrado no sistema.
     *
     * @param cpf o CPF a ser verificado.
     * @return true se o CPF existir, false caso contrário.
     */
    public Boolean verificarCpfExistente(String cpf) {
        return ocorrenciaRepository.verificarCpfExistente(cpf);
    }

    /**
     * Lista todas as ocorrências registradas no sistema.
     *
     * @return uma lista de ocorrências.
     */
    public List<Ocorrencia> listarOcorrencias() {
        return ocorrenciaRepository.findAll();
    }

    /**
     * Atualiza o status de uma ocorrência pelo ID.
     *
     * @param idoco      o ID da ocorrência.
     * @param novoStatus o novo status da ocorrência.
     * @throws IllegalArgumentException se a ocorrência não for encontrada.
     */
    public void atualizarStatusOcorrencia(Long idoco, String novoStatus) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(idoco)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada"));
        ocorrencia.setStatus(novoStatus);
        ocorrenciaRepository.save(ocorrencia);
    }
}
