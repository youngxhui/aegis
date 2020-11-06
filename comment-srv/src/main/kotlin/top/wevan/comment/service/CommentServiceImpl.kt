package top.wevan.comment.service

import org.apache.dubbo.config.annotation.DubboReference
import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import top.wevan.comment.po.CommentPo
import top.wevan.comment.repository.CommentRepository
import top.wevan.common.dto.CommentDto
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

    @DubboReference
    private lateinit var userService: UserService

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
        commentPage.map {
            commentToResponse(comment = it)
        }
        return PageDto()
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
        TODO("Not yet implemented")
    }

    private fun commentToResponse(comment: CommentPo): CommentDto {

//        val user = userRepository.findNameAndAvatarAndAccountById(comment.userId)
//        val loginUserId = (SecurityContextHolder.getContext().authentication.principal as User).id
//        val liked = likedManager.getUserAndCommentLike(userId = loginUserId, commentId = comment.id)

        val commentDto = CommentDto()
//        BeanUtils.copyProperties(comment, commentDto)
//        commentDto.like = liked.type
////        commentDto.account = user.account
////        commentDto.userAvatar = user.avatar
////        commentDto.username = user.name
//        println("comment = [${commentDto}]")
        return commentDto
    }
}