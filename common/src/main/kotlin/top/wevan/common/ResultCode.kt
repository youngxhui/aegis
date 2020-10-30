package top.wevan.common


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

enum class ResultCode(private val code: Long, private val message: String) : IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    override fun getCode(): Long {
        return code
    }

    override fun getMessage(): String {
        return message
    }

}