package br.edu.unoesc.CID.controller;

import br.edu.unoesc.CID.entity.AcessoCidadao;
import br.edu.unoesc.CID.entity.AcessoPolicial;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pelo gerenciamento de usuários.
 * Oferece endpoints para cadastro e autenticação de usuários.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

    protected final UsuarioService usuarioService;

    /**
     * Endpoint para cadastrar um usuário civil.
     *
     * @param usuario       os dados do usuário a ser cadastrado.
     * @param acessoCidadao os dados de acesso do usuário civil.
     * @return uma mensagem de sucesso ou erro.
     */
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

    /**
     * Endpoint para cadastrar um usuário policial.
     *
     * @param usuario       os dados do usuário a ser cadastrado.
     * @param acessoPolicial os dados de acesso do usuário policial.
     * @return uma mensagem de sucesso ou erro.
     */
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

    /**
     * Endpoint para autenticar um usuário com base no CPF e senha.
     *
     * @param cpf   o CPF do usuário.
     * @param senha a senha do usuário.
     * @return uma mensagem indicando sucesso ou erro na autenticação.
     */
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
