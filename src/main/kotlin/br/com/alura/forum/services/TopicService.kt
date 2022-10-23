package br.com.alura.forum.services

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.dto.input.UpdateTopicInput
import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.mappers.input.NewTopicInputMapper
import br.com.alura.forum.mappers.output.TopicOutputMapper
import br.com.alura.forum.models.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic> = mutableListOf(),
    private val newTopicInputMapper: NewTopicInputMapper,
    private val topicOutputMapper: TopicOutputMapper,
) {
    fun listAll(): List<TopicOutput> {
        return topics.map { topic ->
            topicOutputMapper.map(topic)
        }
    }

    fun findById(id: Long): TopicOutput {
        val topic = topics.find {
            it.id == id
        }
        if (topic != null) {
            return topicOutputMapper.map(topic)
        }
        throw Exception("Invalid id provided")
    }

    fun register(dto: NewTopicInput) {
        topics = topics.plus(
            newTopicInputMapper
                .map(dto)
                .apply {
                    id = topics.size.toLong() + 1
                }
        )
    }

    fun update(dto: UpdateTopicInput) {
        val topic = topics.find { it.id == dto.id }
        if (topic != null) {
            topics = topics
                .minus(topic)
                .plus(
                    topic.apply {
                        title = dto.title
                        message = dto.message
                    }
                )
        }
    }
}
