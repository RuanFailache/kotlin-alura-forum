package br.com.alura.forum.repositories

import br.com.alura.forum.models.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
    fun findByCourseName(course_name: String): List<Topic>
}
