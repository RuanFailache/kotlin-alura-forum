package br.com.alura.forum.services

import br.com.alura.forum.models.Course
import org.springframework.stereotype.Service

@Service
class CourseService(var courses: List<Course> = emptyList()) {
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "Programação"
        )
        courses = listOf(course)
    }

    fun findById(id: Long): Course {
        return courses.find { it.id == id }.run lambda@{
            if (this != null) return@lambda this
            throw Exception("There is no course registered with the id received")
        }
    }
}