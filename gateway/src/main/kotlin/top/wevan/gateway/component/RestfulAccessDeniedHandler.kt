package top.wevan.gateway.component

import cn.hutool.json.JSONUtil
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import top.wevan.common.Result
import java.nio.charset.Charset


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

@Component
class RestfulAccessDeniedHandler : ServerAccessDeniedHandler {

    private var logger = LoggerFactory.getLogger(this::class.java)


    override fun handle(exchange: ServerWebExchange, denied: AccessDeniedException): Mono<Void> {
        val response: ServerHttpResponse = exchange.response
        response.statusCode = HttpStatus.OK
        response.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        logger.info(denied.message)
        val body = JSONUtil.toJsonStr(Result(data = denied.message.toString()))
        val buffer = response.bufferFactory().wrap(body.toByteArray(Charset.forName("UTF-8")))
        return response.writeWith(Mono.just(buffer))
    }


}