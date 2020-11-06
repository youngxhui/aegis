package top.wevan.gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.util.PathMatcher
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import top.wevan.gateway.config.IgnoreUrlsConfig
import java.net.URI


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

@Component
class IgnoreUrlsRemoveJwtFilter : WebFilter {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var ignoreUrlsConfig: IgnoreUrlsConfig

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        var exchange = exchange
        var request: ServerHttpRequest = exchange.request
        val uri: URI = request.uri
        logger.info("uri ${uri.path}")
        val pathMatcher: PathMatcher = AntPathMatcher()
        //白名单路径移除JWT请求头
        val ignoreUrls = ignoreUrlsConfig.urls
        for (ignoreUrl in ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.path)) {
                request = exchange.request.mutate().header("Authorization", "").build()
                exchange = exchange.mutate().request(request).build()
                return chain.filter(exchange)
            }
        }
        return chain.filter(exchange)
    }
}