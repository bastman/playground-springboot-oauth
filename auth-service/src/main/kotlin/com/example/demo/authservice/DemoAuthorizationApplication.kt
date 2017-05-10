package com.example.demo.authservice

import com.example.demo.authservice.util.runWeb
import com.example.demo.logging.AppLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.time.Instant
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@SpringBootApplication
@EnableResourceServer
open class DemoAuthorizationApplication
 //   : WebMvcConfigurerAdapter()
{

    private val LOGGER = AppLogger.get(DemoAuthorizationApplication::class.java)

    @Bean
    open fun init(
            ctx: ConfigurableApplicationContext,
            @Value("\${app.appName}") appName: String
    ) = CommandLineRunner {
        LOGGER.info("=== init $appName - $ctx =====")
    }


}

fun main(args: Array<String>) {
    runWeb(DemoAuthorizationApplication::class, *args)
}