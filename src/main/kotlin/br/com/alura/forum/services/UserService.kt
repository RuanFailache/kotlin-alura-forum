package br.com.alura.forum.services

import br.com.alura.forum.models.User
import org.springframework.stereotype.Service

@Service
class UserService(var users: List<User> = emptyList()) {
    init {
        val user = User(
            id = 1,
            name = "Ruan",
            email = "ruan@email.com"
        )
        users = listOf(user)
    }

    fun findById(id: Long): User {
        return users.find { it.id == id }.run lambda@{
            if (this != null) return@lambda this
            throw Exception("There is no course registered with the id received")
        }
    }
}