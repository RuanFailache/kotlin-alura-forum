package br.com.alura.forum.services

import br.com.alura.forum.models.Course
import br.com.alura.forum.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {
    fun findById(id: Long): Course {
        return repository.getReferenceById(id)
    }
}