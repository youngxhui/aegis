package top.wevan.message.component

import org.apache.dubbo.common.json.JSONObject
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest


/**
 * Create by young on 2020/10/29
 * Copyright © 2020 young. All rights reserved.
 */
@Component
class LoginUserHolder {

    fun getCurrentUser():String {
        val servletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
        val request: HttpServletRequest = servletRequestAttributes!!.request
        val userStr: String = request.getHeader("user")
        return userStr
//        val userJsonObject = JSONObject(userStr)
    }

}