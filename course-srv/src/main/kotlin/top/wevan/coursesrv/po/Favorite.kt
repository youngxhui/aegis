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
class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var objectId: Long = 0
    var userId: Long = 0
    var type: Long = 0

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true


    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp
}