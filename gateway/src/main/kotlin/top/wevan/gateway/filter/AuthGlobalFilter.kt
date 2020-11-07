package top.wevan.gateway.filter

import cn.hutool.core.util.StrUtil
import com.nimbusds.jose.JWSObject
import org.slf4j.Logger

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.expression.ParseException
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

@Component
class AuthGlobalFilter : GlobalFilter, Ordered {


    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        var ex = exchange
        val token = ex.request.headers.getFirst("Authorization")
        logger.info(token)
        if (StrUtil.isEmpty(token)) {
            return chain.filter(ex)
        }
        try {
            //从token中解析用户信息并设置到Header中去
            val realToken = token!!.replace("Bearer ", "")
            val jwsObject = JWSObject.parse(realToken)
            val userStr = jwsObject.payload.toString()
            logger.info("AuthGlobalFilter.filter() user:{}", userStr)
            val request: ServerHttpRequest = ex.request.mutate().header("user", userStr).build()
            ex = ex.mutate().request(request).build()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return chain.filter(ex)
    }

    override fun getOrder() = 0


}