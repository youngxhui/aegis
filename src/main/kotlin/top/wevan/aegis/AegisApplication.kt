package top.wevan.aegis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AegisApplication

fun main(args: Array<String>) {
    runApplication<AegisApplication>(*args)
}
