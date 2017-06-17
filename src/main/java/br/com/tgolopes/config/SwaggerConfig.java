package br.com.tgolopes.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.tgolopes.CampanhaApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = CampanhaApplication.class)
public class SwaggerConfig {
    
    private static final String PACKAGE_BASE = "br.com.tgolopes.controller";
    
    @Bean
    public Docket swaggerInit() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(PACKAGE_BASE))
                .paths(PathSelectors.any()).build().apiInfo(this.apiInfo()).useDefaultResponseMessages(false);
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo("campanha", "Serviço Rest para CRUD da Campanha", null, null,
                new Contact("Thiago Oloveira Lopes", null, "thiago.csgo@gmail.com"), null, null);
    }

}

