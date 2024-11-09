package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // Rollback - se algo der errado tudo e desfeito
@RequiredArgsConstructor
public class UsuarioService {

    protected final UsuarioRepository usuarioRepository;

    //metodo de cadastro
    public Usuario cadastrarUsuario(Usuario usuario) {
        //verifica se o usuario ja esta cadastrado
        if(usuarioRepository.findByCPF(usuario.getCpfPessoa()) != null){
            throw new IllegalArgumentException("Usuario ja cadastrado");
        }
        // salva o usuario no banco de dados
        return usuarioRepository.save(usuario);
    }

    //metodo de login
    public Usuario entrarUsuario(String cpfPessoa, String senhaPessoa) {
        //busca o usuario pelo cpf
        Usuario usuario = usuarioRepository.findByCPF(cpfPessoa);

        // verefica se o usuario foi encontrado
        if(usuario == null){
            throw new IllegalArgumentException("Usuario nao cadastrado");
        }

        //verifica se a senha esta correta
        if(!usuario.equals(usuario.getSenhaUsuario())){
            throw new IllegalArgumentException("Senha incorreto");
        }

        return usuario;
    }
}
