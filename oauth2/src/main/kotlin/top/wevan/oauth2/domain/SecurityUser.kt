package top.wevan.oauth2.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.function.Consumer


/**
 * Create by young on 2020/10/27
 * Copyright © 2020 young. All rights reserved.
 */

class SecurityUser() : UserDetails {
    var id: Long = 1

    private lateinit var username: String
    private lateinit var password: String
    private var enable: Boolean = true
    private lateinit var authorities: MutableCollection<SimpleGrantedAuthority>

//    constructor(){}

    constructor(userDTO: UserDTO) : this() {
        this.id = userDTO.id
        this.username = userDTO.username
        this.password = userDTO.password
        this.enable = userDTO.status == 1.toByte()
        if (userDTO.roles.isNotEmpty()) {
            authorities = ArrayList<SimpleGrantedAuthority>()
            userDTO.roles.forEach(Consumer { item: String? ->
                authorities.add(
                    SimpleGrantedAuthority(item)
                )
            })
        }
    }

    /**
     * Returns the authorities granted to the user. Cannot return `null`.
     *
     * @return the authorities, sorted by natural key (never `null`)
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }


    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    override fun getPassword(): String {
        return password
    }

    /**
     * Returns the username used to authenticate the user. Cannot return `null`.
     *
     * @return the username (never `null`)
     */
    override fun getUsername(): String {
        return username
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return `true` if the user's account is valid (ie non-expired),
     * `false` if no longer valid (ie expired)
     */
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return `true` if the user is not locked, `false` otherwise
     */
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return `true` if the user's credentials are valid (ie non-expired),
     * `false` if no longer valid (ie expired)
     */
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return `true` if the user is enabled, `false` otherwise
     */
    override fun isEnabled(): Boolean {
        return enable
    }

    override fun toString(): String {
        return "SecurityUser(" +
                "id=$id, " +
                "username='$username', " +
                "password='$password', " +
                "enable=$enable, " +
                "authorities=$authorities" +
                ")"
    }


}