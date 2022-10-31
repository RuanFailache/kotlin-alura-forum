package br.com.alura.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var title: String,

    var message: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: Author,

    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic = StatusTopic.WAITING_FOR_ANSWER,

    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = emptyList(),
)
