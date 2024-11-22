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

/**
 * Serviço responsável pela gestão dos usuários (cidadãos e policiais).
 */
@Service
@Transactional // Rollback - se algo der errado, tudo é desfeito
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoCidadaoRepository acessoCidadaoRepository;

    @Autowired
    private AcessoPolicialRepository acessoPolicialRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Cadastra um novo usuário civil no sistema.
     *
     * @param usuario o objeto que contém os dados do usuário.
     * @param acessoCidadao as informações de acesso para o cidadão.
     * @return o usuário cadastrado.
     * @throws IllegalArgumentException se o CPF já estiver cadastrado ou se os dados obrigatórios estiverem ausentes.
     */
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

    /**
     * Cadastra um novo usuário policial no sistema.
     *
     * @param usuario o objeto que contém os dados do usuário.
     * @param acessoPolicial as informações de acesso para o policial.
     * @return o usuário cadastrado.
     * @throws IllegalArgumentException se a matrícula já estiver cadastrada ou se os dados obrigatórios estiverem ausentes.
     */
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

    /**
     * Realiza o login de um usuário.
     *
     * @param cpf o CPF do usuário.
     * @param senha a senha fornecida pelo usuário.
     * @return o usuário autenticado.
     * @throws IllegalArgumentException se o usuário não for encontrado ou se a senha estiver incorreta.
     */
    public Usuario entrarUsuario(String cpf, String senha) {
        Usuario usuario = usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        if (!passwordEncoder.matches(senha, usuario.getSenhaUsuario())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }
        return usuario;
    }

    /**
     * Busca um usuário pelo CPF.
     *
     * @param cpf o CPF do usuário.
     * @return o usuário encontrado.
     * @throws IllegalArgumentException se o usuário não for encontrado com o CPF informado.
     */
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o CPF informado."));
    }
}
