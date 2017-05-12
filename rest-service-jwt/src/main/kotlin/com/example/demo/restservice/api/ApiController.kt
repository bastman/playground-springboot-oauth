package com.example.demo.restservice.api

import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("*"))
class ApiController {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/api")
    fun hello(): String {
        return "hello world"
    }

    /* Maps to all HTTP actions by default (GET,POST,..)*/
    val users: String
        @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/api/users")
        @ResponseBody
        get() = "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," + "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}"
}