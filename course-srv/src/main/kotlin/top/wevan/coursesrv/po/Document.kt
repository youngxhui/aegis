package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Table(
    indexes = [
        Index(name = "id", columnList = "id"),
        Index(name = "course_id", columnList = "courseId")]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var name: String? = null
    var url: String? = null
    var picture: String? = null
    var courseId: Long = 0

    var grade = 0.0

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp
}