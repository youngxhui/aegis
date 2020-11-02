package top.wevan.coursesrv.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import top.wevan.coursesrv.po.CoursePo


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */
@Repository
interface CourseRepository : JpaRepository<CoursePo, Long> {
    /**
     * 获取所有可用的课程
     */
    fun findAllByEnable(enable: Boolean,page: Pageable): Page<CoursePo>
}