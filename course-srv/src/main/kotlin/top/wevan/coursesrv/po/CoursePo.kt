package top.wevan.coursesrv.po

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import javax.persistence.*


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */

@Table(
    name = "course", indexes = [
        Index(name = "id", columnList = "id"),
        Index(name = "tip_id", columnList = "tipId"),
        Index(name = "sub_tip_id", columnList = "subTipId"),
        Index(name = "level_id", columnList = "levelId")]
)
@Entity
@EntityListeners(AuditingEntityListener::class)
class CoursePo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    /**
     * 课程名称
     */
    lateinit var name: String

    /**
     * 课程封面
     */
    var cover: String? = null

    /**
     * 类型id
     */
    var tipId: Long = 0

    /**
     * 子类型id
     */
    var subTipId: Long = 0

    /**
     * 介绍
     */
    var description: String? = null

    /**
     * 机构
     */
    var institution: String? = null

    /**
     * 难度等级 id
     */
    var levelId: Long = 0

    /**
     * 目录
     */
    var catalog: String? = null

    /**
     * 课程地址
     */
    var url: String? = null

    /**
     * 授课时长
     */
    var period: Long = 0

    /**
     * 授课教师名字
     */
    lateinit var teacher: String

    @Column(name = "is_online", columnDefinition = "boolean default true")
    var online: Boolean = true

    @CreatedDate
    lateinit var createTime: Timestamp

    @LastModifiedDate
    lateinit var updateTime: Timestamp

    @Column(name = "is_check", columnDefinition = "boolean default true")
    var check: Boolean = true

    @Column(name = "is_enable", columnDefinition = "boolean default true")
    var enable: Boolean = true

    override fun toString(): String {
        return "Course(id=$id, name='$name', cover=$cover, tipId=$tipId, subTipId=$subTipId, description=$description, institution=$institution, levelId=$levelId, catalog=$catalog, url=$url, period=$period, online=$online,check=$check, enable=$enable)"
    }


}