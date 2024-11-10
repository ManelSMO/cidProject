package br.edu.unoesc.CID.controller;


import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.service.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Ocorrencias")
@RequiredArgsConstructor

public class OcorrenciaController {
    private final OcorrenciaService ocorrenciaService;

    @PostMapping("/Registrar")
    public ResponseEntity<Ocorrencia> novaOcorrencia(@RequestBody Ocorrencia ocorrencia){
        try{
           Ocorrencia novaOcorrencia = ocorrenciaService.novaOcorrencia(ocorrencia);
           return ResponseEntity.status(HttpStatus.CREATED).body(novaOcorrencia);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> consultaOcorrencia(@PathVariable Long id){
        try{
            Ocorrencia ocorrencia = ocorrenciaService.consultarOcorrencia(id);
            return ResponseEntity.ok(ocorrencia);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
