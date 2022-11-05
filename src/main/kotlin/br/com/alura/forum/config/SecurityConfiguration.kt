package br.com.alura.forum.config

import br.com.alura.forum.security.JWTAuthenticationFilter
import br.com.alura.forum.security.JWTloginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration(
    private val jwtUtil: JWTUtil,
    private val configuration: AuthenticationConfiguration,
) {
    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/topics").hasAuthority("READ_AND_WRITE")
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()

        http.addFilterBefore(
            JWTloginFilter(jwtUtil = jwtUtil, authManager = configuration.authenticationManager),
            UsernamePasswordAuthenticationFilter().javaClass
        )

        http.addFilterBefore(
            JWTAuthenticationFilter(jwtUtil = jwtUtil),
            UsernamePasswordAuthenticationFilter::class.java
        )

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }
}
