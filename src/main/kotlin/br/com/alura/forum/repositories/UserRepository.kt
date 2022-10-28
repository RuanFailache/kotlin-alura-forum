package br.com.alura.forum.repositories

import br.com.alura.forum.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>