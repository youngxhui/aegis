package top.wevan.oauth2.handler

import top.wevan.common.Result

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import org.springframework.web.bind.annotation.ResponseBody


/**
 * Create by young on 2020/10/30
 * Copyright © 2020 young. All rights reserved.
 */
@ControllerAdvice
class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = [OAuth2Exception::class])
    fun handleOauth2(e: OAuth2Exception): Result? {
        return Result(data = e.message)
    }
}