package top.wevan.message.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.wevan.message.component.LoginUserHolder


/**
 * Create by young on 2020/10/29
 * Copyright © 2020 young. All rights reserved.
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var userLoginHolder: LoginUserHolder

    @GetMapping("/")
    fun currentUser(): String {
        return userLoginHolder.getCurrentUser()
    }

}