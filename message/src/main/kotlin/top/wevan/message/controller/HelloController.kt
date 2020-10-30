package top.wevan.message.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Create by young on 2020/10/29
 * Copyright © 2020 young. All rights reserved.
 */

@RestController
class HelloController {


    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}