package top.wevan.comment.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.dubbo.config.annotation.DubboReference
import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import top.wevan.comment.po.CommentPo
import top.wevan.comment.repository.CommentRepository
import top.wevan.common.dto.CommentDto
import top.wevan.common.dto.HeaderDto
import top.wevan.common.dto.PageDto
import top.wevan.common.service.CommentService
import top.wevan.common.service.UserService


/**
 * Create by young on 2020/11/5
 * Copyright © 2020 young. All rights reserved.
 */
@Service
@DubboService
class CommentServiceImpl : CommentService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var commentRepository: CommentRepository

    /**
     * 保存的 comment
     * @param commentDto 要保存的评论
     */
    override fun saveComment(commentDto: CommentDto) {
        val comment = CommentPo()
        BeanUtils.copyProperties(commentDto, comment)
        logger.info("commentParam = [${comment}]")
        commentRepository.save(comment)
    }

    /**
     * 通过课程id 获取评论
     * @param courseId Long 课程id
     * @param page Int 页数
     * @param size Int 每页数量
     * @return PageDto<CommentDto>
     */
    override fun findCommentByCourseId(courseId: Long, enable: Boolean, page: Int, size: Int): PageDto<CommentDto> {
        val pageable = PageRequest.of(page, size)
        val commentPage = commentRepository.findByCourseIdAndEnable(courseId, true, pageable)
        val commentDtoPage = commentPage.map {
            val pageDto = commentToResponse(comment = it)
            return@map pageDto
        }
        return commentDtoPage
    }

    /**
     * 通过 id 删除 评论
     * @param id 评论id
     */
    override fun deleteCommentById(id: Long) {
        TODO("Not yet implemented")
    }

    /**
     * 获取所有评论
     * @param page Int 页数
     * @param size Int 每页数量
     * @return PageDto<CommentDto>
     */
    override fun getAllComment(page: Int, size: Int): PageDto<CommentDto> {
        TODO("Not yet implemented")
    }

    /**
     * 获取评论数量
     */
    override fun countComment(): Long {
        return commentRepository.count()
    }

    private fun commentToResponse(comment: Page<CommentPo>): PageDto<CommentDto> {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val header = request.getHeader("user")
        val mapper = ObjectMapper()
        val headerDto = mapper.readValue(header, HeaderDto::class.java)
//       val liked = likedManager.getUserAndCommentLike(userId = loginUserId, commentId = comment.id)

        val commentDto = CommentDto()
        BeanUtils.copyProperties(comment, commentDto)
        return commentDto
    }
}