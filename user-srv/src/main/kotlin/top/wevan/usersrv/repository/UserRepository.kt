package top.wevan.usersrv.repository

import org.springframework.data.mongodb.repository.MongoRepository
import top.wevan.usersrv.entity.User


/**
 * Create by young on 2020/10/26
 * Copyright © 2020 young. All rights reserved.
 */

interface UserRepository : MongoRepository<User, String> {
}