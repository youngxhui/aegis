package top.wevan.comment.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import top.wevan.comment.po.CommentPo


/**
 * Create by young on 2020/11/5
 * Copyright © 2020 young. All rights reserved.
 */
@Repository
interface CommentRepository : MongoRepository<CommentPo, Long> {

    /**
     * 通过课程id查找评论
     * @param courseId Long 课程id
     * @param enable Boolean 是否可用
     * @return Page<CommentPo>
     */
    fun findByCourseIdAndEnable(courseId: Long, enable: Boolean, page: Pageable): Page<CommentPo>
}