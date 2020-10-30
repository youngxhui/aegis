package top.wevan.oauth2.po

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@Entity
@Table(name = "user_role")
class UserRole {
    @Id
    var id: Long = 1L
    var roleId = 1L
    var userId = 1L
}