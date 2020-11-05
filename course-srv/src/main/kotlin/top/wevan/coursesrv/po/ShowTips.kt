package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Table(
        name = "show_tips",
        indexes = [Index(name = "id", columnList = "id"),
            Index(name = "tip_id", columnList = "tipId")]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class ShowTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var tipId: Long = 0
    var groupId: Long = 0

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true
}