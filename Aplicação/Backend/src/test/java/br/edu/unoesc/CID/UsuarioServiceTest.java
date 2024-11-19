package br.edu.unoesc.CID;

import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.UsuarioRepository;
import br.edu.unoesc.CID.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void cadastrarUsuario_DeveSalvarUsuario_QuandoDadosValidos() {
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");
        usuario.setSenhaUsuario("senha123");

        when(usuarioRepository.findByCPF(usuario.getCpfPessoa())).thenReturn(null);
        when(passwordEncoder.encode(usuario.getSenhaUsuario())).thenReturn("senhaCodificada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.cadastrarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("12345678901", resultado.getCpfPessoa());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void cadastrarUsuario_DeveLancarExcecao_QuandoCPFJaExiste() {
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");
        usuario.setSenhaUsuario("senha123");

        when(usuarioRepository.findByCPF(usuario.getCpfPessoa())).thenReturn(usuario);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.cadastrarUsuario(usuario);
        });

        assertEquals("Usuário já cadastrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    public void entrarUsuario_DeveRetornarUsuario_QuandoCredenciaisValidas() {
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");
        usuario.setSenhaUsuario("senhaCodificada");

        when(usuarioRepository.findByCPF("12345678901")).thenReturn(usuario);
        when(passwordEncoder.matches("senha123", "senhaCodificada")).thenReturn(true);

        Usuario resultado = usuarioService.entrarUsuario("12345678901", "senha123");

        assertNotNull(resultado);
        assertEquals("12345678901", resultado.getCpfPessoa());
    }

    @Test
    public void entrarUsuario_DeveLancarExcecao_QuandoSenhaInvalida() {
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");
        usuario.setSenhaUsuario("senhaCodificada");

        when(usuarioRepository.findByCPF("12345678901")).thenReturn(usuario);
        when(passwordEncoder.matches("senhaErrada", "senhaCodificada")).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.entrarUsuario("12345678901", "senhaErrada");
        });

        assertEquals("Senha incorreta", exception.getMessage());
    }
}
