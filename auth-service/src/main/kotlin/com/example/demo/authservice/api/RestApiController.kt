package com.example.demo.authservice.api

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
open class RestApiController {

    @RequestMapping("/user")
    fun user(user: Principal): Principal {
        return user
    }
}