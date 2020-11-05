package top.wevan.common.exception


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

enum class ResultCode( val code: Int,  val message: String) {
    SUCCESS(100, "操作成功"),
    VALIDATE_FAILED(200, "参数检验失败"),
    NotResource(300, "没有找到对应资源"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    FAILED(800, "操作失败");

}