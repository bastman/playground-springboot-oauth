package com.example.demo.restservice

import com.example.demo.logging.AppLogger
import com.example.demo.restservice.util.runWeb
import org.apache.catalina.filters.RequestDumperFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

@SpringBootApplication

open class DemoRestApplication {
    private val LOGGER = AppLogger.get(DemoRestApplication::class.java)

    @Bean
    open fun init(
            ctx: ConfigurableApplicationContext,
            @Value("\${app.appName}") appName: String
    ) = CommandLineRunner {
        LOGGER.info("=== init $appName - $ctx =====")

    }

    @Profile("!cloud")
    @Bean
    open fun requestDumperFilter(): RequestDumperFilter {
        return RequestDumperFilter()
    }
}

fun main(args: Array<String>) {
    runWeb(DemoRestApplication::class, *args)
}