package com.ues.crm_backend.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Класс настроек CORS policy.
 */
@Configuration
@EnableWebMvc
public class CorsConfig extends WebMvcConfigurerAdapter {

    /**
     * Метод, определяющий какие эндпоинты могут быть доступны локально.
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("*");
    }
}
