package top.wevan.oauth2.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import top.wevan.oauth2.po.RolePO


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@Repository
interface RoleRepository : JpaRepository<RolePO, Long> {

    @Query(
        value = "select * from roles where id in (select role_id from user_role where user_id = :userId) ",
        nativeQuery = true
    )
    fun queryRolePOByUserId(@Param("userId") userId: Long): List<RolePO>
}