package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.Anexos;
import br.edu.unoesc.CID.service.AnexosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gerenciar operações relacionadas a anexos de ocorrências.
 * Exponibiliza endpoints para adicionar anexos e listar anexos por ocorrência.
 */
@RestController
@RequestMapping("/anexos")
public class AnexosController {

    @Autowired
    private AnexosService anexoService;

    /**
     * Endpoint para adicionar um novo anexo a uma ocorrência.
     *
     * @param anexo o anexo a ser adicionado, deve ser passado no corpo da requisição.
     * @return o anexo adicionado.
     */
    @PostMapping("/adicionar")
    public ResponseEntity<Anexos> adicionarAnexo(@Valid @RequestBody Anexos anexo) {
        Anexos novoAnexo = anexoService.adicionarAnexo(anexo);
        return ResponseEntity.ok(novoAnexo);
    }

    /**
     * Endpoint para listar todos os anexos relacionados a uma ocorrência específica.
     *
     * @param idOcorrencia o ID da ocorrência para a qual os anexos serão listados.
     * @return uma lista de anexos vinculados à ocorrência especificada.
     */
    @GetMapping("/ocorrencia/{idOcorrencia}")
    public List<Anexos> listarAnexosPorOcorrencia(@PathVariable Long idOcorrencia) {
        return anexoService.listarAnexosPorOcorrencia(idOcorrencia);
    }
}


