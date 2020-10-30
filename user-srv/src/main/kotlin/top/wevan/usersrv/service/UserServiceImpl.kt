package top.wevan.usersrv.service

import top.wevan.common.dto.UserDTO
import org.apache.dubbo.config.annotation.DubboService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import top.wevan.common.service.UserService
import top.wevan.usersrv.entity.User
import top.wevan.usersrv.repository.UserRepository


/**
 * Create by young on 2020/10/26
 * Copyright © 2020 young. All rights reserved.
 */
@Service
@DubboService
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository
    override fun findUserById(id: String): UserDTO {
        val user = userRepository.findById(id).orElseGet {
            val user = User()
            user.id = "1"
            user.password = "123"
            user.username = "2322"
            return@orElseGet user
        }
        val userDto = UserDTO()
        BeanUtils.copyProperties(user, userDto)
        return userDto

    }


}