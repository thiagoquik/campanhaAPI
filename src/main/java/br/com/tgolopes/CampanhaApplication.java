package br.com.tgolopes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.tgolopes.config.SwaggerConfig;

@SpringBootApplication
public class CampanhaApplication {
    public static void main(String[] args) {
    	new SpringApplicationBuilder(SwaggerConfig.class).run(args);
    }
}