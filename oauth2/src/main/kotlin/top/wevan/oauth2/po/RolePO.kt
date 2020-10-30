package top.wevan.oauth2.po

import javax.persistence.*


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@Entity
@Table(name = "roles")
class RolePO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0L

    lateinit var name: String
}