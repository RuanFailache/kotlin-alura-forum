package br.com.alura.forum.services

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.dto.input.UpdateTopicInput
import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mappers.input.NewTopicInputMapper
import br.com.alura.forum.mappers.output.TopicOutputMapper
import br.com.alura.forum.models.Topic
import br.com.alura.forum.repositories.TopicRepository
import org.springframework.stereotype.Service

const val notFoundMessage = "There is no topics registered with this id"

@Service
class TopicService(
    private val repository: TopicRepository,
    private val newTopicInputMapper: NewTopicInputMapper,
    private val topicOutputMapper: TopicOutputMapper,
) {
    fun listAll(
        courseName: String?,
    ): List<TopicOutput> {
        val topics: List<Topic> = if (courseName == null) {
            repository.findAll()
        } else {
            repository.findByCourseName(courseName)
        }
        return topics.map { topic -> topicOutputMapper.map(topic) }
    }

    fun findById(id: Long): TopicOutput {
        return repository
            .findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
            .let { topic -> topicOutputMapper.map(topic) }
    }

    fun register(dto: NewTopicInput): TopicOutput {
        return newTopicInputMapper
            .map(dto)
            .let { topic ->
                repository.save(topic)
                topicOutputMapper.map(topic)
            }
    }

    fun update(dto: UpdateTopicInput): TopicOutput {
        return repository
            .findById(dto.id)
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
            .apply {
                title = dto.title
                message = dto.message
            }
            .let { topic ->
                topicOutputMapper.map(topic)
            }
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}
