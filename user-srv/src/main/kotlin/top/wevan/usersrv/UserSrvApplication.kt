package top.wevan.usersrv

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
class UserSrvApplication

fun main(args: Array<String>) {
    runApplication<UserSrvApplication>(*args)
}
