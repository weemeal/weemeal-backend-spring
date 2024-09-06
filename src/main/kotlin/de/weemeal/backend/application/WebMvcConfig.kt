package de.weemeal.backend.application

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://localhost:3000/",
                "http://localhost:3003/",
                "http://192.168.178.36:4026/",
                "https://weemeal.darthkali.duckdns.org/",
                "https://weemeal.darthkali.com"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE")
    }
}
