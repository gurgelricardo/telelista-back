package br.rickgurgel.telelista.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:19006") // Specify the origins that are allowed to access the resources
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify the allowed HTTP methods
                .allowedHeaders("Authorization", "Content-Type") // Specify the allowed headers
                .exposedHeaders("Authorization", "Content-Type") // Specify the headers that should be exposed to the browser
                .allowCredentials(true) // Allow credentials such as cookies or HTTP authentication
                .maxAge(3600); // Maximum amount of time, in seconds, that a preflight request can be cached
    }
}
