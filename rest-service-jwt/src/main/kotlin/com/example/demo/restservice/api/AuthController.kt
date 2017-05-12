package com.example.demo.restservice.api

import com.example.demo.restservice.auth.ApiAccountService
import com.example.demo.restservice.auth.TokenAuthenticationService
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin(origins = arrayOf("*"))
class AuthController(
        private val apiAccountService: ApiAccountService
        // ,private val tokenAuthenticationService: TokenAuthenticationService
) {

    data class LoginRequest(val username: String, val password: String)
    data class LoginResponse(val bearer: String)

    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/auth/login")
    fun login(
            @RequestBody req: LoginRequest,
            @ApiIgnore servletResponse: HttpServletResponse
    ): LoginResponse {

        val account = apiAccountService.accounts.find {
            it.user == req.username && it.password == req.password
        }
        if (account == null) {
            throw BadCredentialsException("Bad Cred!");
        }

        val jwtBuilder = TokenAuthenticationService.createdJwt(account.user)
        val responseAuthHeader = TokenAuthenticationService
                .BasicAuthResponseHeader
                .of(jwtBuilder)

        servletResponse.addHeader(responseAuthHeader.name, responseAuthHeader.value)
        val bearer = jwtBuilder.compact()

        return LoginResponse(bearer = bearer)
    }
}