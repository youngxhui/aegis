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
    fun findAllByEnable(enable: Boolean, page: Pageable): Page<CoursePo>

    /**
     * 通过 tipId 获取课程
     * @param tipId 类型id
     * @param enable 是否可用
     * @param page 分页参数
     */
    fun findAllByTipIdAndEnable(tipId: Long, enable: Boolean, page: Pageable): Page<CoursePo>

    /**
     * 通过 subTipId 获取所有的课程
     * @param subTipId SubTipId 子分类id
     * @param enable Boolean 是否可用
     * @param page Pageable 分页参数
     * @return Page<CoursePo>
     */
    fun findAllBySubTipIdAndEnable(subTipId: Long, enable: Boolean, page: Pageable): Page<CoursePo>
}