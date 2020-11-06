package top.wevan.common.service

import top.wevan.common.dto.CourseDto
import top.wevan.common.dto.PageDto


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */

interface CourseService {
    /**
     * 获取所有的课程
     */
    fun allCourses(page: Int, size: Int): PageDto<CourseDto>

    /**
     * 获取目前可用的课程
     */
    fun allEnableCourses(page: Int, size: Int, enable: Boolean): PageDto<CourseDto>

    /**
     * 通过id获取课程
     * @param courseId 课程id
     */
    fun findCourseById(courseId: Long): CourseDto

    /**
     * 保存课程
     * @param courseDto 课程
     */
    fun saveCourse(courseDto: CourseDto)

    /**
     * 根据tipId获取课程列表
     */
    fun findAllByTipId(tipId: Long, enable: Boolean, page: Int, size: Int): PageDto<CourseDto>

    /**
     * 根据subTipId和tipId获取课程列表
     */
    fun findAllBySubTip(subTipId: Long, page: Int, size: Int): PageDto<CourseDto>


    /**
     * 更新是否可用的状态
     */
    fun updateEnableState(courseId: Long): Boolean


    /**
     * 统计所有的课程数量
     */
    fun countCourse(): Long

    /**
     * 从`url`获取课程信息
     */
//    fun getCourseInfoFromUrl(url: String): CourseInfoResponse
}