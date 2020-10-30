package top.wevan.gateway.component

import cn.hutool.json.JSONUtil
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import top.wevan.gateway.api.CommonResult
import java.nio.charset.Charset


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@Component
class RestAuthenticationEntryPoint : ServerAuthenticationEntryPoint {
    override fun commence(exchange: ServerWebExchange, e: AuthenticationException): Mono<Void> {
        val response = exchange.response
        response.statusCode = HttpStatus.OK
        response.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val body = JSONUtil.toJsonStr(CommonResult.unauthorized(e.message!!))
        val buffer = response.bufferFactory().wrap(body.toByteArray(Charset.forName("UTF-8")))
        return response.writeWith(Mono.just(buffer))
    }
}