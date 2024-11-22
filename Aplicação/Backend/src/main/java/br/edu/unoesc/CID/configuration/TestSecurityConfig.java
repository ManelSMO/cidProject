package br.edu.unoesc.CID.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança para testes da aplicação.
 *
 * A anotação {@link Configuration} indica que esta classe é uma classe de configuração do Spring
 * que define o comportamento da segurança para a aplicação durante os testes.
 * A configuração desabilita a proteção CSRF e permite todas as requisições HTTP sem necessidade de autenticação.
 */
@Configuration
public class TestSecurityConfig {

    /**
     * Configura a segurança da aplicação, permitindo o acesso irrestrito a todas as requisições HTTP.
     * Além disso, desabilita a proteção CSRF (Cross-Site Request Forgery) para facilitar os testes.
     *
     * @param http a instância {@link HttpSecurity} usada para configurar as regras de segurança.
     * @return uma instância de {@link SecurityFilterChain} configurada com as regras definidas.
     * @throws Exception se houver erro na configuração da segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita a proteção CSRF para facilitar os testes
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()); // Permite todas as requisições sem autenticação
        return http.build();
    }
}
