package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "tip", indexes = [Index(name = "id", columnList = "id")])
@EntityListeners(AuditingEntityListener::class)
class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
    lateinit var name: String

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp
}