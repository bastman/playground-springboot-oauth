package com.example.demo.authservice.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.core.annotation.Order
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter





@Configuration
@EnableAuthorizationServer
open class Oauth2AuthorizationServer(
        private val authenticationManager: AuthenticationManager
): AuthorizationServerConfigurerAdapter() {
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .inMemory()
                .withClient("foo")
                .secret("foosecret")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .scopes("custom-scope1", "custom-scope2")
    }
}

@Configuration
open class MvcConfig : WebMvcConfigurerAdapter() {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("login").setViewName("login")
        registry.addViewController("/").setViewName("index")
    }
}

@Configuration
@Order(-20)
open class LoginConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
    }
}