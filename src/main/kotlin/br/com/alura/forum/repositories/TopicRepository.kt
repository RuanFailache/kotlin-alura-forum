package br.com.alura.forum.repositories

import br.com.alura.forum.models.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(
        course_name: String,
        pageable: Pageable
    ): Page<Topic>
}
