package br.com.alura.forum.services

import br.com.alura.forum.models.Author
import br.com.alura.forum.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthorService(private val repository: UserRepository) {
    fun findById(id: Long): Author {
        return repository.getReferenceById(id)
    }
}