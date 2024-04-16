package com.example.stajProjeYeni.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // İzin verilen kökeni belirleyin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP yöntemlerini belirleyin
                .allowedHeaders("*") // İzin verilen başlıkları belirleyin
                .allowCredentials(true); // İzin verilen çerezleri belirtin
    }
}
