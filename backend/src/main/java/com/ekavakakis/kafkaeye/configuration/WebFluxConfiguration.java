package com.ekavakakis.kafkaeye.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedMethods("DELETE", "GET", "POST", "PATCH", "PUT")
                .allowedHeaders("Access- Control-Allow-Headers","Access-Control-Allow-Origin",
                        "Access-Control-Request-Method", "Access-Control-Request-Headers","Origin",
                        "Cache-Control", "Content-Type", "Authorization");
    }
}
