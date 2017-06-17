#Processo seletivo para Desenvolvedor Java
Repositório para armazenar as aplicações desenvolvidas para o processo seletivo para desenvolvedor java

#Frameworks e ferramentas utilizadas
O projeto foi desenvolvido utilizando os seguintes tecnologias:
- Java 8
- Spring Boot
- Spring Data
- Tomcat embutido
- HSQLDB embutido
- JPA
- Swagger
- Maven
- JUNIT

#Arquitetura do projeto
- Ambiente de execução embutido - Utilizado o Tomcat embutido, disponibilizado pelo SpringBoot.
- REST - Esta aplicação disponibiliza um serviço REST para disponibilizar recursos de uma API de cadastro de campanhas. Recebe consome e responde no formato JSON.
- Banco de Dados embutido - Utilizado o banco de dados HSQLDB em memória.

#Pré requisitos
- Java 8
- Maven
	
#Configurações

Os arquivos de propriedades da aplicação se encontram no caminho: src/main/resources

-Application.properties : arquivo de propriedades do Spring
-data.sql: Este arquivo possui a carga de dados inicial da aplicação. Este arquivo é executado automaticamente quando a aplicação for iniciada.

A aplicação está configurada para subir na porta 8081. Caso haja necessidade de alterar esta porta é só alterar o número dela na propriedade server.port do arquivo Application.properties.

#Execução do projeto
Maven: $ mvn clean package spring-boot:run

Utilizando uma IDE: Executar a classe /campanha/src/main/java/br/com/tgolopes/CampanhaApplication.java

No seu browser digite http://localhost:8081/swagger-ui.html por aqui você verá a documentação dos serviços da API.
