package br.com.alura.forum.services

import br.com.alura.forum.models.Author
import br.com.alura.forum.repositories.AuthorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val repository: AuthorRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(user)
    }

    fun findById(id: Long): Author {
        return repository.getReferenceById(id)
    }
}