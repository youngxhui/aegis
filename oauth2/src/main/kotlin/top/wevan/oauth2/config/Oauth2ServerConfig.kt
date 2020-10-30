package top.wevan.oauth2.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import top.wevan.oauth2.component.JwtTokenEnhancer
import top.wevan.oauth2.service.UserServiceImpl
import java.security.KeyPair
import java.util.*


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */
@Configuration
@EnableAuthorizationServer
class Oauth2ServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userDetailsService: UserServiceImpl

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtTokenEnhancer: JwtTokenEnhancer

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory().withClient("client-app")
            .secret(passwordEncoder.encode("123456"))
            .scopes("all")
            .authorizedGrantTypes("password", "refresh_token")
            .accessTokenValiditySeconds(3600)
            .refreshTokenValiditySeconds(86400)
    }

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val enhancerChain = TokenEnhancerChain()
        val delegates: MutableList<TokenEnhancer> = ArrayList()
        delegates.add(jwtTokenEnhancer)
        delegates.add(accessTokenConverter())
        enhancerChain.setTokenEnhancers(delegates) //配置JWT的内容增强器
        endpoints.authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService) //配置加载用户信息的服务
            .accessTokenConverter(accessTokenConverter())
            .tokenEnhancer(enhancerChain)
    }

    @Throws(java.lang.Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.allowFormAuthenticationForClients()
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setKeyPair(keyPair())
        return jwtAccessTokenConverter
    }


    @Bean
    fun keyPair(): KeyPair {
        //从classpath下的证书中获取秘钥对
        val keyStoreKeyFactory = KeyStoreKeyFactory(ClassPathResource("jwt.jks"), "123456".toCharArray())
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray())
    }

//    @Bean
//    fun tokenEnhancer() :TokenEnhancer {
//        return JwtTokenEnhancer()
//    }
}