package top.wevan.oauth2.domain


/**
 * Create by young on 2020/10/29
 * Copyright © 2020 young. All rights reserved.
 */

class Oauth2TokenDTO {

    lateinit var token: String

    lateinit var refreshToken: String

    lateinit var tokenHead: String

    var expiresIn = 0
}