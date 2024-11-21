package br.edu.unoesc.CID.controller;


import br.edu.unoesc.CID.entity.AcessoCidadao;
import br.edu.unoesc.CID.entity.AcessoPolicial;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

    protected final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(
            @RequestBody Usuario usuario,
            @RequestBody AcessoCidadao acessoCidadao) {

        try {
            usuarioService.cadastrarUsuario(usuario, acessoCidadao);
            return ResponseEntity.ok("Usuário Civil cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar-policial")
    public ResponseEntity<String> cadastrarUsuarioPolicial(
            @RequestBody Usuario usuario,
            @RequestBody AcessoPolicial acessoPolicial) {

        try {
            usuarioService.cadastrarUsuarioPolicial(usuario, acessoPolicial);
            return ResponseEntity.ok("Usuário Policial cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/entrar")
    public ResponseEntity<String> entrarUsuario(@RequestParam String cpf, @RequestParam String senha) {
        try {
            Usuario usuario = usuarioService.entrarUsuario(cpf, senha);
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

