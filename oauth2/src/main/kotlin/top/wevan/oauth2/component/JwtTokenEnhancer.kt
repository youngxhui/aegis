package top.wevan.oauth2.component

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component
import top.wevan.oauth2.domain.SecurityUser
import java.util.*


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */

@Component
class JwtTokenEnhancer : TokenEnhancer {
    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val securityUser = authentication.principal as SecurityUser
        val info: MutableMap<String, Any> = HashMap()
        //把用户ID设置到JWT中
        info["id"] = securityUser.id
        (accessToken as DefaultOAuth2AccessToken).additionalInformation = info
        return accessToken
    }
}