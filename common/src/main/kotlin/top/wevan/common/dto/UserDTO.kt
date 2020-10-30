package top.wevan.common.dto

import java.io.Serializable

/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */

class UserDTO : Serializable {
    lateinit var id: String
    lateinit var password: String
    lateinit var username: String
}