package br.com.outsera.movie.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {


  @Bean
  public OpenAPI myOpenAPI() {

    Contact contact = new Contact();
    contact.setEmail("ruylm@hotmail.com");
    contact.setName("Ruy Leal");

    Info info = new Info()
        .title("Tutorial Management API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage tutorials.");

    return new OpenAPI().info(info);
  }
}
