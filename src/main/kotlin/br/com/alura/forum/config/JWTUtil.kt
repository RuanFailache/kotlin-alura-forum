package br.com.alura.forum.config

import br.com.alura.forum.services.AuthorService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

const val second: Long = 1000
const val minute: Long = 60 * second

@Component
class JWTUtil(
    private val userService: AuthorService
) {
    private val expiration: Date = Date(System.currentTimeMillis() + 5 * minute)

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String?, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser()
                .setSigningKey(secret.toByteArray())
                .parseClaimsJws(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(jwt).body.subject

        val user = userService.loadUserByUsername(username)

        return UsernamePasswordAuthenticationToken(
            username,
            null,
            user.authorities
        )
    }
}
