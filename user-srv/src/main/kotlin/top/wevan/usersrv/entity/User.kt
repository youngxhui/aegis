package top.wevan.usersrv.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*


/**
 * Create by young on 2020/10/26
 * Copyright © 2020 young. All rights reserved.
 */
@Document
class User {
    @Id
    var id = UUID.randomUUID().toString()
    lateinit var password: String
    lateinit var username: String
}