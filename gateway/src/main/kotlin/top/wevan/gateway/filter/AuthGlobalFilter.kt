package top.wevan.gateway.filter

import cn.hutool.core.util.StrUtil
import com.nimbusds.jose.JWSObject
import org.slf4j.Logger

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
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
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        var exchange = exchange
        val token = exchange.request.headers.getFirst("Authorization")
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange)
        }
        try {
            //从token中解析用户信息并设置到Header中去
            val realToken = token!!.replace("Bearer ", "")
            val jwsObject = JWSObject.parse(realToken)
            val userStr = jwsObject.payload.toString()
            LOGGER.info("AuthGlobalFilter.filter() user:{}", userStr)
            val request: ServerHttpRequest = exchange.request.mutate().header("user", userStr).build()
            exchange = exchange.mutate().request(request).build()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return chain.filter(exchange)
    }

    override fun getOrder(): Int {
        return 0
    }


    private val LOGGER: Logger = LoggerFactory.getLogger(AuthGlobalFilter::class.java)

}