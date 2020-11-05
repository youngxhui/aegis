package top.wevan.common.exception


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 * 运行时结果异常处理
 * @param message 错误信息
 * @param code 错误码
 */
class ResultException(message: String,var code:Int) : Exception(message)