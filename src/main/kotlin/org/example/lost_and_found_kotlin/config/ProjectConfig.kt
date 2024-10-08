package org.example.lost_and_found_kotlin.config

import org.example.lost_and_found_kotlin.security.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun configure(http: HttpSecurity, customAuthenticationProvider: CustomAuthenticationProvider): SecurityFilterChain {
        http.httpBasic(Customizer.withDefaults())

        http.authenticationProvider(customAuthenticationProvider)

        http.authorizeHttpRequests { c -> c.anyRequest().authenticated() }

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun customAuthenticationProvider(): AuthenticationProvider {
        return CustomAuthenticationProvider(passwordEncoder())
    }

}