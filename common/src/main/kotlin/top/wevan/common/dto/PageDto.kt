package top.wevan.common.dto

import java.io.Serializable


/**
 * Create by young on 2020/11/3
 * Copyright © 2020 young. All rights reserved.
 */

class PageDto<T> : Serializable {
    /**
     * 数据大小
     */
    var size: Int = 0

    /**
     * 页数
     */
    var page: Int = 0

    lateinit var content: List<T>

    var totalPages: Int = 0

    var totalElements: Long = 0L

}