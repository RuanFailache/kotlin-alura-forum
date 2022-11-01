package br.com.alura.forum.repositories

import br.com.alura.forum.models.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository : JpaRepository<Author, Long>