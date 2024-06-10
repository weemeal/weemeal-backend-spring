package de.weemeal.weemealbackendspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["de.weemeal.backend"])
@EnableJpaRepositories(basePackages = ["de.weemeal.backend.adapter.out.persistence"])
@EntityScan("de.weemeal.backend.adapter.out.persistence.entities")
class WeemealBackendSpringApplication

fun main(args: Array<String>) {
    runApplication<WeemealBackendSpringApplication>(*args)
}
