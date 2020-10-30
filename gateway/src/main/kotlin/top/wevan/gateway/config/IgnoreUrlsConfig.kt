package top.wevan.gateway.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@Component
@ConfigurationProperties(prefix = "secure.ignore")
class IgnoreUrlsConfig {
    //    var urls: List<String> = listOf("/oauth/auth/**", "/actuator/**")
    lateinit var urls: List<String>
}