package com.example.demo.restservice.auth

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class ApiAccountService {

    val accounts = listOf<Account>(
            Account(
                    user = "admin",
                    password = "password",
                    authorities = listOf(Authority.ADMINISTRATOR, Authority.DEVELOPER)
            )
    )


    data class Account(
            val user: String,
            val password: String,
            val authorities: List<Authority>
    )

    enum class Authority(val role: String) {
        ADMINISTRATOR("ADMINISTRATOR"),
        DEVELOPER("DEVELOPER"),
        USER("USER");

        override fun toString(): String {
            return role
        }

        fun toSimpleGrantedAuthority(): SimpleGrantedAuthority {
            return SimpleGrantedAuthority(role)
        }
    }
}
