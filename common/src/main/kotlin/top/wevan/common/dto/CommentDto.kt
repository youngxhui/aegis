package top.wevan.common.dto

import java.sql.Timestamp


/**
 * Create by young on 2020/11/5
 * Copyright © 2020 young. All rights reserved.
 */

class CommentDto {

    var id: Long = 0
    var content: String? = null
    var score: Long = 0
    var recommend: Long = 0
    var costPerformance: Long = 0
    var picture: String? = null
    var userId: Long = 0
    var courseId: Long = 0


    lateinit var createTime: Timestamp


    lateinit var updateTime: Timestamp
    var top: Long = 0

    var like: Long = 0
    var dislike: Long = 0

    var enable: Boolean = true

    override fun toString(): String {
        return "Comment(id=$id, content=$content, score=$score, recommend=$recommend, costPerformance=$costPerformance, picture=$picture, userId=$userId, courseId=$courseId, top=$top, like=$like, dislike=$dislike, enable=$enable)"
    }
}