package top.wevan.oauth2.service

import org.slf4j.LoggerFactory
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import top.wevan.oauth2.constant.*
import top.wevan.oauth2.domain.SecurityUser
import top.wevan.oauth2.domain.UserDTO
import top.wevan.oauth2.repository.RoleRepository
import top.wevan.oauth2.repository.UserRepository


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */
@Service
class UserServiceImpl : UserDetailsService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    private var userList: MutableList<UserDTO> = ArrayList()

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var roleRepository: RoleRepository


//    @PostConstruct
//    fun initData() {
//        val password = passwordEncoder.encode("123456")
//        val user1 = UserDTO();
//        user1.id = 1
//        user1.username = "macro"
//        user1.password = password
//        user1.status = 1
//        user1.roles = listOf("ADMIN")
//        userList.add(user1)
//    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the `UserDetails`
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never `null`)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     * GrantedAuthority
     */
    override fun loadUserByUsername(email: String): UserDetails {
        logger.info("login email => $email")
        val userPO = userRepository.findUserPOByEmail(email) ?: throw UsernameNotFoundException(USERNAME_PASSWORD_ERROR)
        val rolePOs = roleRepository.queryRolePOByUserId(userPO.id)
        val userDTO = UserDTO()
        BeanUtils.copyProperties(userPO, userDTO)
        rolePOs.forEach {
            userDTO.roles.add(it.name)
        }
        val securityUser = SecurityUser(userDTO)
        if (!securityUser.isEnabled) {
            throw DisabledException(ACCOUNT_DISABLED)
        } else if (!securityUser.isAccountNonLocked) {
            throw LockedException(ACCOUNT_LOCKED)
        } else if (!securityUser.isAccountNonExpired) {
            throw AccountExpiredException(ACCOUNT_EXPIRED)
        } else if (!securityUser.isCredentialsNonExpired) {
            throw CredentialsExpiredException(CREDENTIALS_EXPIRED)
        }
        return securityUser
    }
}