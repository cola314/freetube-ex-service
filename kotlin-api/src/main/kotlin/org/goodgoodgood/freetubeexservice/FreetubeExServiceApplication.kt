package org.goodgoodgood.freetubeexservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class FreetubeExServiceApplication

fun main(args: Array<String>) {
    runApplication<FreetubeExServiceApplication>(*args)
}