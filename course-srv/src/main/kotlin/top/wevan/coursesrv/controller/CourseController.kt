package top.wevan.coursesrv.controller

import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import top.wevan.common.service.UserService


/**
 * Create by young on 2020/10/26
 * Copyright © 2020 young. All rights reserved.
 */
@RestController
class CourseController {
    @DubboReference
    private lateinit var userService: UserService

    @GetMapping("/{id}")
    fun index(@PathVariable("id") id: String): String {
        val user = userService.findUserById(id)
        return user.toString()
    }


}