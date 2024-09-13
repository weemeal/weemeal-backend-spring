package de.weemeal.backend.application.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val corsProperties: CorsProperties) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(*corsProperties.allowedOrigins!!.toTypedArray<String>())
            .allowedMethods(*corsProperties.allowedMethods!!.toTypedArray<String>())
    }
}