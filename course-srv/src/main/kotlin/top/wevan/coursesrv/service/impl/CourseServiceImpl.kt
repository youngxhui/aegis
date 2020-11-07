package top.wevan.coursesrv.service.impl

import org.apache.dubbo.config.annotation.DubboService
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import top.wevan.common.exception.ResultCode
import top.wevan.common.dto.CourseDto
import top.wevan.common.dto.PageDto
import top.wevan.common.exception.ResultException
import top.wevan.common.service.CourseService
import top.wevan.coursesrv.po.CoursePo
import top.wevan.coursesrv.repository.CourseRepository
import org.springframework.web.context.request.RequestContextHolder

import org.springframework.web.context.request.ServletRequestAttributes


/**
 * Create by young on 2020/11/2
 * Copyright © 2020 young. All rights reserved.
 */
@DubboService
@Service
class CourseServiceImpl : CourseService {

    private var logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var courseRepository: CourseRepository

    /**
     * 获取所有的课程
     */
    override fun allCourses(page: Int, size: Int): PageDto<CourseDto> {

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val header = request.getHeader("user")
        logger.info(header)
        val pageable = PageRequest.of(page, size)

        val all: Page<CoursePo> = courseRepository.findAll(pageable)

        return page2PageDto(all)
    }

    /**
     * 获取所有可使用的课程
     */
    override fun allEnableCourses(page: Int, size: Int, enable: Boolean): PageDto<CourseDto> {
        val pageable = PageRequest.of(page, size)
        val all = courseRepository.findAllByEnable(enable, pageable)

        return page2PageDto(all)
    }


    /**
     * 通过课程id找到对应的课程
     */
    override fun findCourseById(courseId: Long): CourseDto {
        val coursePo = courseRepository.findById(courseId).orElseThrow {
            throw ResultException("没有该课程", ResultCode.NotResource.code)
        }
        val courseDto = CourseDto()
        BeanUtils.copyProperties(coursePo, courseDto)
        return courseDto
    }

    /**
     * 根据tipId获取课程列表
     */
    override fun findAllByTipId(tipId: Long, enable: Boolean, page: Int, size: Int): PageDto<CourseDto> {
        val page = PageRequest.of(page, size)
        val courseOfPage = courseRepository.findAllByTipIdAndEnable(tipId, true, page)
        return page2PageDto(courseOfPage)
    }

    /**
     * 根据subTipId和tipId获取课程列表
     * @param subTipId 子分类id
     * @param page 页数
     * @param size 数量
     */
    override fun findAllBySubTip(subTipId: Long, enable: Boolean, page: Int, size: Int): PageDto<CourseDto> {
        val pageable = PageRequest.of(page, size)
        val coursePage = courseRepository.findAllBySubTipIdAndEnable(subTipId, enable, pageable)
        return page2PageDto(coursePage)
    }

    /**
     * 更新是否可用的状态
     * @param courseId 课程id
     */
    override fun updateEnableState(courseId: Long): Boolean {
        val course =
            courseRepository.findById(courseId)
                .orElseThrow { throw ResultException("没有该课程", ResultCode.NotResource.code) }
        course.enable = !course.enable
        val courseUpdate = courseRepository.save(course)
        return courseUpdate.enable
    }

    /**
     * 统计所有的课程数量
     */
    override fun countCourse(): Long {
        return courseRepository.count()
    }

    /**
     * 保存课程信息
     * @param courseDto CourseDto
     */
    override fun saveCourse(courseDto: CourseDto) {

        val course = CoursePo()

        BeanUtils.copyProperties(courseDto, course)

        courseRepository.save(course)
    }


    private fun page2PageDto(coursePos: Page<CoursePo>): PageDto<CourseDto> {
        val pageDto = PageDto<CourseDto>()
        BeanUtils.copyProperties(coursePos, pageDto)
        return pageDto
    }
}