package br.edu.unoesc.CID;

import br.edu.unoesc.CID.entity.PessoaEnvolvida;
import br.edu.unoesc.CID.repository.PessoaEnvolvidaRepository;
import br.edu.unoesc.CID.service.PessoaEnvolvidaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PessoaEnvolvidaServiceTest {
    @Mock
    private PessoaEnvolvidaRepository pessoaEnvolvidaRepository;

    @InjectMocks
    private PessoaEnvolvidaService pessoaEnvolvidaService;

    @Test
    public void listarPessoasEnvolvidasPorOcorrencia_DeveRetornarListaDePessoas() {
        // Configuração
        PessoaEnvolvida pessoa1 = new PessoaEnvolvida();
        pessoa1.setNomePessoaEnvolvida("João");
        pessoa1.setCpfPessoaEnvolvida("12345678901");

        PessoaEnvolvida pessoa2 = new PessoaEnvolvida();
        pessoa2.setNomePessoaEnvolvida("Maria");
        pessoa2.setCpfPessoaEnvolvida("98765432100");

        List<PessoaEnvolvida> pessoas = Arrays.asList(pessoa1, pessoa2);

        // Configuração do Mock
        when(pessoaEnvolvidaRepository.findByOcorrenciaId(1)).thenReturn(pessoas);

        // Execução
        List<PessoaEnvolvida> resultado = pessoaEnvolvidaService.listarPessoasEnvolvidasPorOcorrencia(1);

        // Verificação
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNomePessoaEnvolvida());
        verify(pessoaEnvolvidaRepository, times(1)).findByOcorrenciaId(1);
    }
}
