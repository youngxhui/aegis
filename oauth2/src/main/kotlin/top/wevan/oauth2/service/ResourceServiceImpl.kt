package top.wevan.oauth2.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import top.wevan.oauth2.constant.RESOURCE_ROLES_MAP
import java.util.*
import javax.annotation.PostConstruct


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@Service
class ResourceServiceImpl {

    private lateinit var resourceRolesMap: MutableMap<String, List<String>>

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @PostConstruct
    fun initData() {
        resourceRolesMap = TreeMap()
        resourceRolesMap["*"] = listOf("ADMIN")
        resourceRolesMap["/api/user/currentUser"] = listOf("ADMIN", "TEST")
        resourceRolesMap["/course/**"] = listOf("ADMIN", "TEST")
        redisTemplate.opsForHash<Any, Any>().putAll(RESOURCE_ROLES_MAP, resourceRolesMap)
    }


}