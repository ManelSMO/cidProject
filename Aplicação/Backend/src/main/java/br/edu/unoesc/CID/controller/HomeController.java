package br.edu.unoesc.CID.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pela página inicial da API.
 * Oferece um endpoint simples para exibir uma mensagem de boas-vindas.
 */
@RestController
public class HomeController {

    /**
     * Endpoint para exibir uma mensagem de boas-vindas.
     *
     * @return a mensagem de boas-vindas.
     */
    @GetMapping("/")
    public String home() {
        return "Bem-vindo à API CID!";
    }
}
