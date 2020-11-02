package top.wevan.common.dto

import java.io.Serializable
import java.sql.Timestamp


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */


class CourseDto : Serializable {

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

    var online: Boolean = true


    lateinit var createTime: Timestamp


    lateinit var updateTime: Timestamp


    var check: Boolean = true


    var enable: Boolean = true

    override fun toString(): String {
        return "Course(id=$id, name='$name', cover=$cover, tipId=$tipId, subTipId=$subTipId, description=$description, institution=$institution, levelId=$levelId, catalog=$catalog, url=$url, period=$period, online=$online,check=$check, enable=$enable)"
    }
}