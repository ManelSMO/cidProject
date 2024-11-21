package br.edu.unoesc.CID;
import br.edu.unoesc.CID.configuration.TestSecurityConfig;
import br.edu.unoesc.CID.controller.OcorrenciaController;
import br.edu.unoesc.CID.entity.Ocorrencia;
import br.edu.unoesc.CID.entity.Usuario;
import br.edu.unoesc.CID.service.OcorrenciaService;
import br.edu.unoesc.CID.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(OcorrenciaController.class)
@Import(TestSecurityConfig.class)
public class OcorrenciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OcorrenciaService ocorrenciaService; // Simula o serviço

    @MockBean
    private UsuarioService usuarioService; // Simula o serviço para usuários

    @Test
    public void deveRegistrarNovaOcorrencia() throws Exception {
        // Dados da ocorrência simulada
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricaoOcorrencia("Roubo de celular");
        ocorrencia.setLocalOcorrencia("Rua A, 123");
        ocorrencia.setDataOcorrencia("2024-11-18");

        // Mock do usuário
        Usuario usuario = new Usuario();
        usuario.setCpfPessoa("12345678901");

        // Configurando os mocks
        when(usuarioService.buscarUsuarioPorCpf("12345678901")).thenReturn(usuario);
        when(ocorrenciaService.novaOcorrencia(any(Ocorrencia.class), eq(usuario))).thenReturn(ocorrencia);

        // Executando a requisição simulada
        mockMvc.perform(post("/ocorrencias/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"descricaoOcorrencia\": \"Roubo de celular\", \"localOcorrencia\": \"Rua A, 123\", \"dataOcorrencia\": \"2024-11-18\" }")
                        .param("cpfUsuario", "12345678901"))
                .andExpect(status().isCreated()) // Verifica o status
                .andExpect(jsonPath("$.descricaoOcorrencia").value("Roubo de celular")) // Verifica os campos
                .andExpect(jsonPath("$.localOcorrencia").value("Rua A, 123"))
                .andExpect(jsonPath("$.dataOcorrencia").value("2024-11-18"));
    }
}

