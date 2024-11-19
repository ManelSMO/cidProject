package br.edu.unoesc.CID;


import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.TipoUsuario;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.repository.OcorrenciaRepository;
import br.edu.unoesc.CID.service.OcorrenciaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OcorrenciaServiceTest {

    @Mock
    private OcorrenciaRepository ocorrenciaRepository;

    @InjectMocks
    private OcorrenciaService ocorrenciaService;

    @Test
    public void novaOcorrencia_DeveSalvarOcorrencia_QuandoDadosValidos() {
        // Configuração
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricaoOcorrencia("Roubo de celular");
        ocorrencia.setLocalOcorrencia("Rua B, 456");
        ocorrencia.setDataOcorrencia("2024-11-18");

        when(ocorrenciaRepository.save(any(Ocorrencia.class))).thenReturn(ocorrencia);

        // Execução
        Ocorrencia resultado = ocorrenciaService.novaOcorrencia(ocorrencia);

        // Verificação
        assertNotNull(resultado);
        assertEquals("Roubo de celular", resultado.getDescricaoOcorrencia());
        assertEquals("Rua B, 456", resultado.getLocalOcorrencia());
        assertEquals("2024-11-18", resultado.getDataOcorrencia());
        verify(ocorrenciaRepository, times(1)).save(any(Ocorrencia.class));
    }

    @Test
    public void consultarOcorrencia_DeveRetornarOcorrencia_QuandoUsuarioAutorizado() {
        // Configuração
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");
        usuario.setTipoUsuario(TipoUsuario.CIVIL);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setIdOcorrencia(1L);
        ocorrencia.setDescricaoOcorrencia("Roubo de celular");
        ocorrencia.setCpfCivil("12345678901");

        when(ocorrenciaRepository.findById(1L)).thenReturn(Optional.of(ocorrencia));

        // Execução
        Ocorrencia resultado = ocorrenciaService.consultarOcorrencia(1L, usuario);

        // Verificação
        assertNotNull(resultado);
        assertEquals("Roubo de celular", resultado.getDescricaoOcorrencia());
        assertEquals("12345678901", resultado.getCpfCivil());
        verify(ocorrenciaRepository, times(1)).findById(1L);
    }
    @Test
    public void consultarOcorrencia_DeveLancarExcecao_QuandoUsuarioNaoAutorizado() {
        // Configuração
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("98765432100"); // CPF não autorizado
        usuario.setTipoUsuario(TipoUsuario.CIVIL);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setIdOcorrencia(1L);
        ocorrencia.setDescricaoOcorrencia("Roubo de celular");
        ocorrencia.setCpfCivil("12345678901");

        when(ocorrenciaRepository.findById(1L)).thenReturn(Optional.of(ocorrencia));

        // Execução e Verificação
        assertThrows(SecurityException.class, () -> {
            ocorrenciaService.consultarOcorrencia(1L, usuario);
        });

        verify(ocorrenciaRepository, times(1)).findById(1L);
    }
    @Test
    public void consultarOcorrencia_DeveRetornarOcorrencia_QuandoFuncionarioAutorizado() {
        // Configuração
        Usuario funcionario = new Usuario();
        funcionario.setCpfPessoa("98765432100");
        funcionario.setTipoUsuario(TipoUsuario.POLICIAL);

        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setIdOcorrencia(1L);
        ocorrencia.setDescricaoOcorrencia("Roubo de celular");

        when(ocorrenciaRepository.findById(1L)).thenReturn(Optional.of(ocorrencia));

        // Execução
        Ocorrencia resultado = ocorrenciaService.consultarOcorrencia(1L, funcionario);

        // Verificação
        assertNotNull(resultado);
        assertEquals("Roubo de celular", resultado.getDescricaoOcorrencia());
        verify(ocorrenciaRepository, times(1)).findById(1L);
    }
    @Test
    public void listarOcorrenciasValidadas_DeveRetornarListaDeOcorrencias() {
        // Configuração
        Ocorrencia ocorrencia1 = new Ocorrencia();
        ocorrencia1.setIdOcorrencia(1L);
        ocorrencia1.setDescricaoOcorrencia("Roubo de celular");
        ocorrencia1.setLocalOcorrencia("Rua A");

        Ocorrencia ocorrencia2 = new Ocorrencia();
        ocorrencia2.setIdOcorrencia(2L);
        ocorrencia2.setDescricaoOcorrencia("Furto de veículo");
        ocorrencia2.setLocalOcorrencia("Rua B");

        List<Object[]> ocorrencias = Arrays.asList(
                new Object[]{1L, "Roubo de celular", "2024-11-18", "Rua A", "VALIDADA"},
                new Object[]{2L, "Furto de veículo", "2024-11-19", "Rua B", "VALIDADA"}
        );

        when(ocorrenciaRepository.listarOcorrenciasValidadas()).thenReturn(ocorrencias);

        // Execução
        List<Object[]> resultado = ocorrenciaService.listarOcorrenciasValidadas();

        // Verificação
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Roubo de celular", resultado.get(0)[1]);
        assertEquals("Furto de veículo", resultado.get(1)[1]);
        verify(ocorrenciaRepository, times(1)).listarOcorrenciasValidadas();
    }
}

