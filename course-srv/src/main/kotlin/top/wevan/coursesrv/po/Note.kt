package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Table(
    name = "note", indexes = [
        Index(name = "id", columnList = "id"),
        Index(name = "course_id", columnList = "courseId"),
        Index(name = "user_id", columnList = "userId")]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var content: String? = null
    var courseId: Long = 0
    var userId: Long = 0

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

}