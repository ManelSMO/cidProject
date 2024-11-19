package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // Rollback - se algo der errado tudo e desfeito
@RequiredArgsConstructor
public class UsuarioService {

    protected final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Método para cadastrar usuário
    public Usuario cadastrarUsuario(Usuario usuario) {
        // Verifica se o CPF já está cadastrado
        if (usuarioRepository.findByCPF(usuario.getCpfPessoa()) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado");
        }

        // Criptografa a senha antes de salvar
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));

        // Salva o usuário no banco
        return usuarioRepository.save(usuario);
    }

    // Método para login de usuário
    public Usuario entrarUsuario(String cpf, String senha) {
        // Busca o usuário pelo CPF
        Usuario usuario = usuarioRepository.findByCPF(cpf);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        // Verifica se a senha corresponde
        if (!passwordEncoder.matches(senha, usuario.getSenhaUsuario())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        return usuario;
    }

}
