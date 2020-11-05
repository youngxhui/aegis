package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Table(
    indexes = [
        Index(name = "id", columnList = "id"),
    ]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var path: String? = null
    var description: String? = null

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp
}