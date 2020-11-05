package top.wevan.oauth2.controller

import top.wevan.common.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import top.wevan.oauth2.domain.Oauth2TokenDTO
import java.security.Principal


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */

@RestController
@RequestMapping("/oauth")
class AuthController {
    @Autowired
    private lateinit var tokenEndpoint: TokenEndpoint

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = ["/token"], method = [RequestMethod.POST])
    @Throws(HttpRequestMethodNotSupportedException::class)
    fun postAccessToken(
        principal: Principal,
        @RequestParam parameters: Map<String, String>
    ): Result {
        val oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).body!!
        val oauth2TokenDto = Oauth2TokenDTO()
        oauth2TokenDto.token = oAuth2AccessToken.value
        oauth2TokenDto.refreshToken = oAuth2AccessToken.refreshToken.value
        oauth2TokenDto.expiresIn = oAuth2AccessToken.expiresIn
        oauth2TokenDto.tokenHead = "Bearer "
        return Result.success(oauth2TokenDto)
    }
}
