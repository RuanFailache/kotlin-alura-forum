package br.com.alura.forum.dto.input

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicInput(
    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    val title: String,

    @field:NotEmpty
    @field:Size(min = 5)
    val message: String,

    @field:NotNull
    val courseId: Long,

    @field:NotNull
    val authorId: Long,
)
