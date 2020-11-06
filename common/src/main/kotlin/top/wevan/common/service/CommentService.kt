package top.wevan.common.service

import top.wevan.common.dto.CommentDto
import top.wevan.common.dto.PageDto


/**
 * Create by young on 2020/11/5
 * Copyright © 2020 young. All rights reserved.
 */

interface CommentService {

    /**
     * 保存的 comment
     * @param commentDto 要保存的评论
     */
    fun saveComment(commentDto: CommentDto)

    /**
     * 通过课程id 获取评论
     * @param courseId Long 课程id
     * @param page Int 页数
     * @param size Int 每页数量
     * @return PageDto<CommentDto>
     */
    fun findCommentByCourseId(courseId: Long, enable: Boolean, page: Int, size: Int): PageDto<CommentDto>

    /**
     * 通过 id 删除 评论
     * @param id 评论id
     */
    fun deleteCommentById(id: Long)


    /**
     * 获取所有评论
     * @param page Int 页数
     * @param size Int 每页数量
     * @return PageDto<CommentDto>
     */
    fun getAllComment(page: Int, size: Int): PageDto<CommentDto>

    /**
     * 获取评论数量
     */
    fun countComment(): Long
}