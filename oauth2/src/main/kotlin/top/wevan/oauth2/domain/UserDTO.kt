package top.wevan.oauth2.domain


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */

class UserDTO {
    var id: Long = 1
    lateinit var email: String
    lateinit var username: String
    lateinit var password: String
    var status: Byte = 0
    var roles: MutableList<String> = ArrayList()

    override fun toString(): String {
        return "UserDTO(id=$id, email='$email', username='$username', password='$password', status=$status, roles=$roles)"
    }


}