package top.wevan.coursesrv

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
class CourseSrvApplication

fun main(args: Array<String>) {
    runApplication<CourseSrvApplication>(*args)
}
