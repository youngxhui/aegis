package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*

/**
 * Create by huazhu cao on 2020/8/2
 * Copyright © 2020 huazhu cao. All rights reserved.
 */
@Table(name = "role", indexes = [Index(name = "id", columnList = "id")])
@Entity
@EntityListeners(AuditingEntityListener::class)
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    lateinit var name: String

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp

}