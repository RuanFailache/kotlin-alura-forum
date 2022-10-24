package br.com.alura.forum.services

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.dto.input.UpdateTopicInput
import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mappers.input.NewTopicInputMapper
import br.com.alura.forum.mappers.output.TopicOutputMapper
import br.com.alura.forum.models.Topic
import org.springframework.stereotype.Service

const val notFoundMessage = "There is no topics registered with this id"

@Service
class TopicService(
    private var topics: List<Topic> = listOf(),
    private val newTopicInputMapper: NewTopicInputMapper,
    private val topicOutputMapper: TopicOutputMapper,
) {
    fun listAll(): List<TopicOutput> {
        return topics.map { topic ->
            topicOutputMapper.map(topic)
        }
    }

    fun findById(id: Long): TopicOutput {
        return topics
            .stream()
            .filter { it.id == id }
            .findFirst()
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
            .let { topic -> topicOutputMapper.map(topic) }
    }

    fun register(dto: NewTopicInput): TopicOutput {
        return newTopicInputMapper
            .map(dto)
            .apply {
                id = topics.size.toLong() + 1
            }
            .let { topic ->
                topics = topics.plus(topic)
                topicOutputMapper.map(topic)
            }
    }

    fun update(dto: UpdateTopicInput): TopicOutput {
        return topics
            .stream()
            .filter {
                it.id == dto.id
            }
            .findFirst()
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
            .let { topic ->
                topics = topics
                    .minus(topic)
                    .plus(topic.apply {
                        title = dto.title
                        message = dto.message
                    })
                topicOutputMapper.map(topic)
            }
    }

    fun delete(id: Long) {
        topics
            .stream()
            .filter {
                it.id == id
            }
            .findFirst()
            .orElseThrow {
                NotFoundException(notFoundMessage)
            }
            .let { topic ->
                topics = topics.minus(topic)
            }
    }
}
