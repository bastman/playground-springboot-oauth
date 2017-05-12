package com.example.demo.restservice.auth

import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by sebastians on 11.05.17.
 */
class TokenAuthenticationService {

    data class BasicAuthResponseHeader(
            val name: String = "Authorization",
            val value: String
    ) {
        companion object {
            fun of(jwtBuilder: JwtBuilder): BasicAuthResponseHeader {
                return BasicAuthResponseHeader(value = "Bearer ${jwtBuilder.compact()}")
            }
        }
    }


    companion object {
        val EXPIRY: Duration = Duration.ofDays(1);
        val SECRET = "ThisIsASecret"
        val TOKEN_PREFIX = "Bearer"
        val HEADER_STRING = "Authorization"

        fun createdJwt(username: String): JwtBuilder {
            val expireAt = Date.from(Instant.now() + EXPIRY)

            return Jwts.builder()
                    .setSubject(username)
                    .setExpiration(expireAt)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
        }

        fun addAuthentication(res: HttpServletResponse, username: String) {

            val JWT = createdJwt(username)
                    .compact()
            res.addHeader(HEADER_STRING, "$TOKEN_PREFIX $JWT")
        }

        fun getAuthentication(req: HttpServletRequest): Authentication? {
            val token: String? = req.getHeader(HEADER_STRING)
            if (token == null) {
                return null
            }

            val jwsParser = Jwts.parser()
                    .setSigningKey(SECRET)

            val jws = jwsParser.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            val user: String? = jws
                    .body
                    .subject

            if (user != null) {
                return UsernamePasswordAuthenticationToken(user, null, emptyList())
            }

            return null;

        }
    }


}