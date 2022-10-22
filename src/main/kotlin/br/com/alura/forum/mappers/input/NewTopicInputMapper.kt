package br.com.alura.forum.mappers.input

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.mappers.helpers.Mapper
import br.com.alura.forum.models.Topic
import br.com.alura.forum.services.CourseService
import br.com.alura.forum.services.UserService
import org.springframework.stereotype.Component

@Component
class NewTopicInputMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<NewTopicInput, Topic> {
    override fun map(item: NewTopicInput): Topic {
        return Topic(
            title = item.title,
            message = item.message,
            course = courseService.findById(item.courseId),
            author = userService.findById(item.authorId),
        )
    }
}
