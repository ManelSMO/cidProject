package br.edu.unoesc.CID.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração do Web MVC para a aplicação.
 *
 * Esta classe implementa {@link WebMvcConfigurer} para fornecer configurações adicionais de
 * recursos estáticos e CORS (Cross-Origin Resource Sharing) para a aplicação.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura os manipuladores de recursos estáticos para servir arquivos de uma pasta específica.
     *
     * Este método mapeia os arquivos da pasta "/_next/**" para o local estático "classpath:/static/_next/",
     * permitindo que a aplicação sirva corretamente os arquivos do frontend.
     *
     * @param registry o {@link ResourceHandlerRegistry} usado para registrar manipuladores de recursos.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/_next/**")
                .addResourceLocations("classpath:/static/_next/");
    }

    /**
     * Configura o CORS para permitir que o frontend acesse a API sem restrições de origem cruzada.
     *
     * Este método configura as origens permitidas (neste caso, http://localhost:3000), os métodos HTTP permitidos
     * (GET, POST, PUT, DELETE, OPTIONS), e os cabeçalhos permitidos. Essencial para permitir que o frontend e o
     * backend se comuniquem sem problemas de CORS durante o desenvolvimento.
     *
     * @return uma instância de {@link WebMvcConfigurer} com configurações de CORS definidas.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // URL do Frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
