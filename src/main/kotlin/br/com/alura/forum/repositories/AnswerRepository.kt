package br.com.alura.forum.repositories

import br.com.alura.forum.models.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long>