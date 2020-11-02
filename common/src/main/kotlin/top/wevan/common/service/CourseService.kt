package top.wevan.common.service

import top.wevan.common.dto.CourseDto


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */

interface CourseService {
    /**
     * 获取所有的课程
     */
    fun allCourses(page: Int, size: Int): List<CourseDto>

    /**
     * 获取目前可用的课程
     */
    fun allEnableCourses(page: Int,size: Int,enable:Boolean): List<CourseDto>

}