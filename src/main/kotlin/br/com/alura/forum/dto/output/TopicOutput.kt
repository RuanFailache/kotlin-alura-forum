package br.com.alura.forum.dto.output

import br.com.alura.forum.models.StatusTopic
import br.com.alura.forum.models.Topic
import java.time.LocalDateTime

data class TopicOutput(
    val id: Long,
    var title: String,
    var message: String,
    val status: StatusTopic,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromTopic(topic: Topic): TopicOutput {
            return topic
                .let {
                    TopicOutput(
                        id = it.id ?: 0,
                        title = it.title,
                        message = it.message,
                        status = it.status,
                        createdAt = it.createdAt,
                    )
                }
        }
    }
}
