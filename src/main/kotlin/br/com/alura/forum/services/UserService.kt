package br.com.alura.forum.services

import br.com.alura.forum.models.User
import br.com.alura.forum.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {
    fun findById(id: Long): User {
        return repository.getReferenceById(id)
    }
}