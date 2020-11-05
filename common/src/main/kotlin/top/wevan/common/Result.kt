package top.wevan.common

import java.io.Serializable


/**
 * Create by young on 2020/10/28
 * Copyright © 2020 young. All rights reserved.
 */

data class Result(var code: Long = 200, var message: String = "成功", var data: Any? = null) : Serializable