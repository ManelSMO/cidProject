package br.edu.unoesc.CID.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Bem-vindo à API! Utilize os endpoints para acessar os serviços.";
    }
}