package de.weemeal.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["de.weemeal.backend.adapter.out.persistence"])
@EntityScan("de.weemeal.backend.adapter.out.persistence.entity")
@EnableConfigurationProperties
class WeemealBackendSpringApplication

fun main(args: Array<String>) {
    runApplication<WeemealBackendSpringApplication>(*args)
}
