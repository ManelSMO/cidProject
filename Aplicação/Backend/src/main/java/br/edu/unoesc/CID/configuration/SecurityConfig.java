package br.edu.unoesc.CID.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe de configuração de segurança para a aplicação.
 *
 * A anotação {@link Configuration} indica que esta classe é usada para configurar beans do Spring,
 * especialmente no que diz respeito à segurança da aplicação.
 * O bean {@link BCryptPasswordEncoder} é utilizado para codificar e decodificar senhas de maneira segura.
 */
@Configuration
public class SecurityConfig {

    /**
     * Cria e fornece um bean {@link BCryptPasswordEncoder} para ser utilizado em outras partes da aplicação.
     * O {@link BCryptPasswordEncoder} utiliza o algoritmo BCrypt para realizar o hash das senhas.
     *
     * @return uma instância de {@link BCryptPasswordEncoder}.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
