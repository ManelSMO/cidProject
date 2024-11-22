package br.edu.unoesc.CID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal para iniciar a aplicação Spring Boot.
 *
 * A anotação {@link SpringBootApplication} configura a aplicação como uma aplicação Spring Boot,
 * com auto-configuração, busca por componentes e configuração de beans.
 */
@SpringBootApplication
public class CidApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args argumentos passados na linha de comando ao iniciar a aplicação.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CidApplication.class, args);
	}
}
