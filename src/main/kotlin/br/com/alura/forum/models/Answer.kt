package br.com.alura.forum.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val message: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val author: Author,

    @ManyToOne
    val topic: Topic,

    val isSolution: Boolean,
)
