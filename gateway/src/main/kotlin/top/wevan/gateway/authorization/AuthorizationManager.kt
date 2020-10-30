package top.wevan.gateway.authorization


import cn.hutool.core.convert.Convert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import top.wevan.gateway.constant.AUTHORITY_PREFIX
import top.wevan.gateway.constant.RESOURCE_ROLES_MAP
import java.net.URI


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@Component
class AuthorizationManager : ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>


    /**
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the Authentication to check
     * @param object the object to check
     * @return an decision or empty Mono if no decision could be made.
     */
    override fun check(
        mono: Mono<Authentication>,
        authorizationContext: AuthorizationContext
    ): Mono<AuthorizationDecision> {
        //从Redis中获取当前路径可访问角色列表
        val uri: URI = authorizationContext.exchange.request.uri

        val obj = redisTemplate.opsForHash<String, Any>().get(RESOURCE_ROLES_MAP, uri.path)
        var authorities: List<String> = Convert.toList(String::class.java, obj)
        authorities = authorities.map {
            AUTHORITY_PREFIX + it
        }
        //认证通过且角色匹配的用户可访问当前路径
        return mono
            .filter { it.isAuthenticated }
            .flatMapIterable { it.authorities }
            .map { it.authority }
            .any { authorities.contains(it) }
            .map { AuthorizationDecision(it) }
            .defaultIfEmpty(AuthorizationDecision(false))
    }
}