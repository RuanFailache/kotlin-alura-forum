package br.com.alura.forum.dto.output

import br.com.alura.forum.models.StatusTopic
import java.time.LocalDateTime

data class TopicOutput(
    val id: Long,
    var title: String,
    var message: String,
    val status: StatusTopic,
    val createdAt: LocalDateTime
)