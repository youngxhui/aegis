package top.wevan.oauth2.controller

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */
@RestController
class KeyPairController {

    @Autowired
    private lateinit var keyPair: KeyPair

    @GetMapping("/rsa/publicKey")
    fun getKey(): JSONObject {
        val publicKey = keyPair.public as RSAPublicKey
        val key = RSAKey.Builder(publicKey).build()
        return JWKSet(key).toJSONObject()
    }


}