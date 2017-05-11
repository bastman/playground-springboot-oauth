package com.example.demo.restservice.auth

import java.time.Duration
import javax.servlet.http.HttpServletResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by sebastians on 11.05.17.
 */
class TokenAuthenticationService {




    companion object{
        val EXPIRY:Duration= Duration.ofDays(1);
        val SECRET="ThisIsASecret"
        val TOKEN_PREFIX="Bearer"
        val HEADER_STRING="Authorization"

        fun addAuthentication(res:HttpServletResponse, username:String) {
            val expireAt = Date.from(Instant.now()+EXPIRY)

            val JWT = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(expireAt)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()
            res.addHeader(HEADER_STRING, "$TOKEN_PREFIX $JWT")
        }

        fun getAuthentication(req:HttpServletRequest):Authentication? {
            val token:String? = req.getHeader(HEADER_STRING)
            if(token==null) {
                return null
            }

            val user:String? = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body
                    .subject

            if(user!=null) {
                return UsernamePasswordAuthenticationToken(user, null, emptyList())
            }

            return null;

        }
    }



}