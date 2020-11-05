package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Table(
    indexes = [
        Index(name = "id", columnList = "id"),
        Index(name = "user_id", columnList = "userId")]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var content: String? = null
    var score: Long = 0
    var recommend: Long = 0
    var costPerformance: Long = 0
    var picture: String? = null
    var userId: Long = 0
    var courseId: Long = 0

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp
    var top: Long = 0

    @Column(name = "`like`")
    var like: Long = 0
    var dislike: Long = 0

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

    override fun toString(): String {
        return "Comment(id=$id, content=$content, score=$score, recommend=$recommend, costPerformance=$costPerformance, picture=$picture, userId=$userId, courseId=$courseId, top=$top, like=$like, dislike=$dislike, enable=$enable)"
    }


}