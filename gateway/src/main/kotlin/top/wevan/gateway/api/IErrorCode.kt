package top.wevan.gateway.api


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

interface IErrorCode {
    fun getCode(): Long
    fun getMessage(): String
}