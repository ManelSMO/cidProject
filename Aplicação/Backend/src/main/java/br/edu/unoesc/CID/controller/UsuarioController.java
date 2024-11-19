package br.edu.unoesc.CID.controller;


import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

    protected final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            // Salva o usuário diretamente
            usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
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

