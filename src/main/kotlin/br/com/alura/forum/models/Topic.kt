package br.com.alura.forum.models

import java.time.LocalDateTime

data class Topic(
    var id: Long? = null,
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: StatusTopic = StatusTopic.WAITING_FOR_ANSWER,
    val answers: List<Answer> = emptyList(),
)
