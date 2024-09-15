package de.weemeal.weemealbackendspring

import de.weemeal.backend.WeemealBackendSpringApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [WeemealBackendSpringApplication::class])
@EnableConfigurationProperties
@ActiveProfiles("test")
class WeemealBackendSpringApplicationTests {

    @Test
    fun contextLoads() {
    }

}
