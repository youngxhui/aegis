package top.wevan.coursesrv.service.impl

import org.apache.dubbo.config.annotation.DubboService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import top.wevan.common.dto.CourseDto
import top.wevan.common.service.CourseService
import top.wevan.coursesrv.po.CoursePo
import top.wevan.coursesrv.repository.CourseRepository


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */
@DubboService
@Service
class CourseServiceImpl : CourseService {

    @Autowired
    private lateinit var courseRepository: CourseRepository

    /**
     * 获取所有的课程
     */
    override fun allCourses(page: Int, size: Int): List<CourseDto> {
        val page = PageRequest.of(page, size)
        val all: Page<CoursePo> = courseRepository.findAll(page)
        return pageable2list(all)
    }

    /**
     * 获取所有可使用的课程
     */
    override fun allEnableCourses(page: Int, size: Int, enable: Boolean): List<CourseDto> {
        val page = PageRequest.of(page, size)
        val all = courseRepository.findAllByEnable(enable, page)
        return pageable2list(all)
    }


    private fun pageable2list(coursePos: Page<CoursePo>): List<CourseDto> {
        return coursePos.map {
            val courseDto = CourseDto()
            BeanUtils.copyProperties(it, courseDto)
            return@map courseDto
        }.toList()
    }

}