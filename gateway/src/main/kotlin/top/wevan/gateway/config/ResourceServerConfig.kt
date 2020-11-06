package top.wevan.gateway.config

import cn.hutool.core.util.ArrayUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import top.wevan.gateway.authorization.AuthorizationManager
import top.wevan.gateway.component.RestAuthenticationEntryPoint
import top.wevan.gateway.component.RestfulAccessDeniedHandler
import top.wevan.gateway.constant.AUTHORITY_CLAIM_NAME
import top.wevan.gateway.constant.AUTHORITY_PREFIX
import top.wevan.gateway.filter.AuthGlobalFilter
import top.wevan.gateway.filter.IgnoreUrlsRemoveJwtFilter


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@Configuration
@EnableWebFluxSecurity
class ResourceServerConfig {
    @Autowired
    private lateinit var authorizationManager: AuthorizationManager

    @Autowired
    private lateinit var ignoreUrlsConfig: IgnoreUrlsConfig

    @Autowired
    private lateinit var restfulAccessDeniedHandler: RestfulAccessDeniedHandler

    @Autowired
    private lateinit var restAuthenticationEntryPoint: RestAuthenticationEntryPoint

    @Autowired
    private lateinit var ignoreUrlsRemoveJwtFilter: IgnoreUrlsRemoveJwtFilter

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {

        http.oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())
//        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint)
        //对白名单路径，直接移除JWT请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
        http.authorizeExchange()
            .pathMatchers(*ArrayUtil.toArray(ignoreUrlsConfig.urls, String::class.java)).permitAll() //白名单配置
            .anyExchange().access(authorizationManager) //鉴权管理器配置
            .and().exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler) //处理未授权
            .authenticationEntryPoint(restAuthenticationEntryPoint) //处理未认证
            .and().csrf().disable()
        return http.build()
    }

    @Bean
    fun jwtAuthenticationConverter(): Converter<Jwt?, out Mono<out AbstractAuthenticationToken?>?>? {
        val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AUTHORITY_PREFIX)
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AUTHORITY_CLAIM_NAME)
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter)
        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
    }

}


