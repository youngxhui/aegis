package top.wevan.comment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentSrvApplication

fun main(args: Array<String>) {
    runApplication<CommentSrvApplication>(*args)
}
