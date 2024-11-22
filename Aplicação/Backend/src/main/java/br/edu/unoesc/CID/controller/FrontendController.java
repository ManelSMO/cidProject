package br.edu.unoesc.CID.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador responsável por gerenciar o redirecionamento das requisições para a página inicial do frontend.
 * Essa classe garante que todas as requisições sem extensão (como .html, .css) sejam direcionadas para o arquivo index.html.
 */
@Controller
@RequestMapping("/")
public class FrontendController {

    /**
     * Método para redirecionar todas as requisições para o arquivo index.html.
     *
     * @return a página inicial do frontend, index.html.
     */
    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";
    }
}
