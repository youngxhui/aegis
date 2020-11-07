package top.wevan.common.dto

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Create by young on 2020/11/7
 * Copyright © 2020 young. All rights reserved.
 */
//{"user_name":"youngxhui","scope":["all"],"id":1,"exp":1604718510,"authorities":["ADMIN"],"jti":"cb982219-3bd4-4d58-b223-26204b167ca3",
// "client_id":"client-app"}
class HeaderDto {
    var id: Long = 1

    @JsonProperty("user_name")
    lateinit var username: String
    lateinit var scpoe: Array<String>
    var exp: Long = 1
    lateinit var authorities: Array<String>
    lateinit var jti: String

    @JsonProperty("client_id")
    lateinit var clientId: String

    override fun toString(): String {
        return "HeaderDto(id=$id, username='$username', scpoe=${scpoe.contentToString()}, exp=$exp, authorities=${authorities.contentToString()}, jti='$jti', clientId='$clientId')"
    }


}