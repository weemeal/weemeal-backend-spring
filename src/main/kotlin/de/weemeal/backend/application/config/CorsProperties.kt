package de.weemeal.backend.application.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cors")
class CorsProperties {
    var allowedOrigins: List<String>? = null
    var allowedMethods: List<String>? = null
}