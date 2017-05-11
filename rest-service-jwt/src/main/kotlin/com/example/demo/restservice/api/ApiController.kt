package com.example.demo.restservice.api

import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/")
    fun hello(): String {
        return "hello world"
    }

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    val users: String
        @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/users")
        @ResponseBody
        get() = "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," + "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}"
}