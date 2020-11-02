package top.wevan.common.service

import top.wevan.common.dto.UserDto


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */

interface UserService {
    fun findUserById(id:String): UserDto
}