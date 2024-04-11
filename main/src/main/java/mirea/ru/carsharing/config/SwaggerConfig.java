package mirea.ru.carsharing.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author upagge 11.04.2024
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("BasicCarSharing Api")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("sidorov.s.d1@edu.mirea.ru")
                                                .name("Sidorov S. D.")
                                )
                );
    }

}