package top.wevan.oauth2.po

import javax.persistence.*


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@Entity
@Table(name = "users")
class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L

    lateinit var email: String

    lateinit var password: String

    lateinit var username: String

    var status: Byte = 1

    override fun toString(): String {
        return "UserPO(" +
                "id=$id, " +
                "email='$email', " +
                "password='$password', " +
                "username='$username', " +
                "status=$status" +
                ")"
    }

}