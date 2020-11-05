package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*


/**
 * Create by young on 2020/8/22
 * Copyright © 2020 young. All rights reserved.
 */
@Entity
@Table(
    name = "liked", indexes = [
        Index(name = "id", columnList = "id"),
        Index(name = "comment_id", columnList = "commentId"),
        Index(name = "user_id", columnList = "userId")]
)
@EntityListeners(AuditingEntityListener::class)
class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var userId: Long = 0

    var commentId: Long = 0

    /**
     * 类型
     * - -1 不喜欢
     * - 0 取消
     * - 1 喜欢
     */
    var type = 0


    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp

    override fun toString(): String {
        return "Liked(id=$id, userId=$userId, commentId=$commentId, type=$type)"
    }


}