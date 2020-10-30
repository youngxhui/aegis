package top.wevan.oauth2.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import top.wevan.oauth2.po.UserPO


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@Repository
interface UserRepository : JpaRepository<UserPO, Long> {
    /**
     * 通过邮箱查找用户
     * @param email 邮箱
     * @return 查找到 `user`
     */
    fun findUserPOByEmail(email: String): UserPO?

}