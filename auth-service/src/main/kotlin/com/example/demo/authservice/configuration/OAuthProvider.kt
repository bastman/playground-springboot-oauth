package com.example.demo.authservice.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.beans.factory.annotation.Autowired



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