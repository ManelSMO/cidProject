package br.edu.unoesc.CID.controller;



import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.OcorrenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/Ocorrencias")
@RequiredArgsConstructor

public class OcorrenciaController {

    @Autowired
    private final OcorrenciaService ocorrenciaService;

    @PostMapping("/registrar")
    public ResponseEntity<Ocorrencia> novaOcorrencia(@Valid @RequestBody Ocorrencia ocorrencia) {
        Ocorrencia novaOcorrencia = ocorrenciaService.novaOcorrencia(ocorrencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOcorrencia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> consultarOcorrencia(@PathVariable Long id, @RequestBody Usuario usuario) {
        Ocorrencia ocorrencia = ocorrenciaService.consultarOcorrencia(id, usuario);
        return ResponseEntity.ok(ocorrencia);
    }

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

    @GetMapping("/validadas")
    public List<Object[]> listarOcorrenciasValidadas() {

        return ocorrenciaService.listarOcorrenciasValidadas();
    }

    @GetMapping("/historico")
    public List<Object[]> consultarHistoricoOcorrencias(@RequestParam String cpf) {
        return ocorrenciaService.consultarHistoricoOcorrencias(cpf);
    }

    @GetMapping("/verificar-cpf")
    public Boolean verificarCpfExistente(@RequestParam String cpf) {
        return ocorrenciaService.verificarCpfExistente(cpf);
    }

    @GetMapping
    public ResponseEntity<List<Ocorrencia>> listarOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaService.listarOcorrencias();
        return ResponseEntity.ok(ocorrencias);
    }

}
