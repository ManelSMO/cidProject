package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.AcessoCidadao;
import br.edu.unoesc.CID.entity.AcessoPolicial;
import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.AcessoCidadaoRepository;
import br.edu.unoesc.CID.repository.AcessoPolicialRepository;
import br.edu.unoesc.CID.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // Rollback - se algo der errado tudo e desfeito
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoCidadaoRepository acessoCidadaoRepository;

    @Autowired
    private AcessoPolicialRepository acessoPolicialRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public Usuario cadastrarUsuario(Usuario usuario, AcessoCidadao acessoCidadao) {
        // Verifica se o CPF já está cadastrado
        if (acessoCidadaoRepository.existsByCpfUsuario(acessoCidadao.getCpfUsuario())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        // Define o tipo de usuário como CIVIL caso não seja informado
        if (usuario.getTipoUsuario() == null || acessoCidadao.getCpfUsuario() == null || acessoCidadao.getSenha() == null) {
            throw new IllegalArgumentException("Dados obrigatórios não informados para cadastro de usuário.");
        }

        // Salva o usuário
        Usuario novoUsuario = usuarioRepository.save(usuario);

        // Configura o acesso de cidadão
        if (usuario.getTipoUsuario() == TipoUsuario.CIVIL) {
            acessoCidadao.setUsuario(novoUsuario);
            acessoCidadaoRepository.save(acessoCidadao);
        } else {
            throw new IllegalArgumentException("Para Policial, use o cadastro apropriado.");
        }

        return novoUsuario;
    }

    public Usuario cadastrarUsuarioPolicial(Usuario usuario, AcessoPolicial acessoPolicial) {
        // Verifica se a matrícula já está cadastrada
        if (acessoPolicialRepository.existsByMatricula(acessoPolicial.getMatricula())) {
            throw new IllegalArgumentException("Matrícula já cadastrada.");
        }

        // Define o tipo de usuário como POLICIAL
        usuario.setTipoUsuario(TipoUsuario.POLICIAL);

        // Verifica se os dados obrigatórios estão informados
        if (acessoPolicial.getMatricula() == null || acessoPolicial.getSenha() == null) {
            throw new IllegalArgumentException("Dados obrigatórios não informados para cadastro de policial.");
        }

        // Salva o usuário
        Usuario novoUsuario = usuarioRepository.save(usuario);

        // Configura o acesso policial
        acessoPolicial.setUsuario(novoUsuario);
        acessoPolicialRepository.save(acessoPolicial);

        return novoUsuario;
    }

    // Método para login de usuário
    public Usuario entrarUsuario(String cpf, String senha) {
        Usuario usuario = usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        if (!passwordEncoder.matches(senha, usuario.getSenhaUsuario())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }
        return usuario;
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o CPF informado."));
    }
}
