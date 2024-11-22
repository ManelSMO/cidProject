package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.OcorrenciaService;
import br.edu.unoesc.CID.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das ocorrências.
 * Fornece endpoints para registrar, consultar e listar ocorrências.
 */
@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    @Autowired
    private final OcorrenciaService ocorrenciaService;

    @Autowired
    private final UsuarioService usuarioService;

    /**
     * Endpoint para registrar uma nova ocorrência vinculada a um usuário.
     *
     * @param ocorrencia a ocorrência a ser registrada.
     * @param cpfUsuario CPF do usuário responsável pela ocorrência.
     * @return a ocorrência registrada.
     */
    @PostMapping("/registrar")
    public ResponseEntity<Ocorrencia> novaOcorrencia(@RequestBody Ocorrencia ocorrencia, @RequestParam String cpfUsuario) {
        // Busca o usuário pelo CPF
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpfUsuario);

        // Registra a ocorrência vinculada ao usuário
        Ocorrencia novaOcorrencia = ocorrenciaService.novaOcorrencia(ocorrencia, usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaOcorrencia);
    }

    /**
     * Endpoint para consultar uma ocorrência específica pelo seu ID.
     *
     * @param id o ID da ocorrência.
     * @param cpfUsuario CPF do usuário que está consultando a ocorrência.
     * @return a ocorrência encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> consultarOcorrencia(@PathVariable Long id, @RequestParam String cpfUsuario) {
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpfUsuario);
        Ocorrencia ocorrencia = ocorrenciaService.consultarOcorrencia(id, usuario);
        return ResponseEntity.ok(ocorrencia);
    }

    /**
     * Endpoint para inserir uma ocorrência diretamente no banco de dados.
     *
     * @param descoco descrição da ocorrência.
     * @param datoco data da ocorrência.
     * @param lococo local da ocorrência.
     * @param staoco status da ocorrência.
     * @param cidadeId ID da cidade relacionada à ocorrência.
     * @param funcionarioId ID do funcionário responsável pela ocorrência.
     * @param tipoViolencia tipo de violência envolvida na ocorrência.
     * @param tipoOcorrencia tipo da ocorrência.
     */
    @PostMapping("/inserir")
    public void inserirOcorrencia(
            @RequestParam String descoco,
            @RequestParam String datoco,
            @RequestParam String lococo,
            @RequestParam String staoco,
            @RequestParam int cidadeId,
            @RequestParam int funcionarioId,
            @RequestParam String tipoViolencia,
            @RequestParam int tipoOcorrencia
    ) {
        ocorrenciaService.inserirOcorrencia(descoco, datoco, lococo, staoco, cidadeId, funcionarioId, tipoViolencia, tipoOcorrencia);
    }

    /**
     * Endpoint para atualizar o status de uma ocorrência.
     *
     * @param idoco ID da ocorrência.
     * @param novoStatus o novo status da ocorrência.
     * @param usuario o usuário que está fazendo a atualização.
     * @return a resposta com o status da atualização.
     */
    @PutMapping("/atualizar-status")
    public ResponseEntity<?> atualizarStatusOcorrencia(
            @RequestParam int idoco,
            @RequestParam String novoStatus,
            Usuario usuario) {
        try {
            ocorrenciaService.atualizarStatusOcorrencia(idoco, novoStatus, usuario);
            return ResponseEntity.ok("Status atualizado com sucesso.");
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    /**
     * Endpoint para listar todas as ocorrências validadas.
     *
     * @return uma lista de ocorrências validadas.
     */
    @GetMapping("/validadas")
    public List<Object[]> listarOcorrenciasValidadas() {
        return ocorrenciaService.listarOcorrenciasValidadas();
    }

    /**
     * Endpoint para consultar o histórico de ocorrências de um usuário.
     *
     * @param cpf CPF do usuário.
     * @return a lista de ocorrências no histórico do usuário.
     */
    @GetMapping("/historico")
    public List<Object[]> consultarHistoricoOcorrencias(@RequestParam String cpf) {
        return ocorrenciaService.consultarHistoricoOcorrencias(cpf);
    }

    /**
     * Endpoint para verificar se um CPF já existe no sistema.
     *
     * @param cpf o CPF a ser verificado.
     * @return um booleano indicando se o CPF existe.
     */
    @GetMapping("/verificar-cpf")
    public Boolean verificarCpfExistente(@RequestParam String cpf) {
        return ocorrenciaService.verificarCpfExistente(cpf);
    }

    /**
     * Endpoint para listar todas as ocorrências no sistema.
     *
     * @return a lista de todas as ocorrências.
     */
    @GetMapping
    public ResponseEntity<List<Ocorrencia>> listarOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaService.listarOcorrencias();
        return ResponseEntity.ok(ocorrencias);
    }
}
