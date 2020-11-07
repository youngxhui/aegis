package top.wevan.comment.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.wevan.common.Result
import top.wevan.common.dto.CommentDto
import top.wevan.common.service.CommentService
import java.sql.Timestamp
import java.time.LocalDateTime


/**
 * Create by young on 2020/11/7
 * Copyright © 2020 young. All rights reserved.
 */
@RestController
@RequestMapping("/comment")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    @GetMapping
    fun saveComment(): Result {

        val commentDto = CommentDto()
        commentDto.id = 1
        commentDto.content = "123你好啊啊啊23333"
        commentDto.score = 2
        commentDto.recommend = 1
        commentDto.costPerformance = 1
        commentDto.picture = "2222.png"
        commentDto.userId = 1
        commentDto.courseId = 1
        commentDto.top = 1
        commentDto.like = 1
        commentDto.enable = true
        commentDto.dislike = 2
        commentDto.createTime = Timestamp.valueOf(LocalDateTime.now())
        commentDto.updateTime = Timestamp.valueOf(LocalDateTime.now())
        commentService.saveComment(commentDto)
        return Result(data = commentDto)
    }


}