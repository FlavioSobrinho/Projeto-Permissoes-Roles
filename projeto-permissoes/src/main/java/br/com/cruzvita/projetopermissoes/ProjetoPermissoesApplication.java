package br.com.cruzvita.projetopermissoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan(basePackages = {"br.com.cruzvita.projetopermissoes"})
public class ProjetoPermissoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPermissoesApplication.class, args);
		
	    }
	
}
