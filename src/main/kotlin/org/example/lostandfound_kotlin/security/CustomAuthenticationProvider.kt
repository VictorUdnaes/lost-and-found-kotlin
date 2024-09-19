package org.example.lostandfound_kotlin.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider (
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    private val encodedPassword: String = passwordEncoder.encode("12345")
    private val log: Logger = LoggerFactory.getLogger(CustomAuthenticationProvider::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        log.info("authenticating user")
        val username: String = authentication?.name.orEmpty()
        val password: String = authentication?.credentials.toString()

        return if (username == "john" && passwordEncoder.matches(password, encodedPassword)) {
            log.info("authentication successful")
            val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
            UsernamePasswordAuthenticationToken(username, password, authorities)
        } else {
            log.error("authentication failed")
            throw AuthenticationCredentialsNotFoundException("Error during authentication")
        }
    }

    override fun supports(authenticationType: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authenticationType)
    }
}