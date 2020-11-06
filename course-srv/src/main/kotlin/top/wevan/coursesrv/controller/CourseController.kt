package top.wevan.coursesrv.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.wevan.common.Result
import top.wevan.common.service.CourseService


/**
 * Create by young on 2020/10/26
 * Copyright © 2020 young. All rights reserved.
 */
@RestController
@RequestMapping("course")
class CourseController {

    @Autowired
    private lateinit var courseService: CourseService


    @GetMapping("/{id}")
    fun index(@PathVariable("id") id: String): Result {
        val page = 0
        val size = 10
        val allCourses = courseService.allCourses(page, size)
        return Result(data = allCourses)
    }


}