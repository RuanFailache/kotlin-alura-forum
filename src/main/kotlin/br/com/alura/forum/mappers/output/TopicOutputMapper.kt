package br.com.alura.forum.mappers.output

import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.mappers.helpers.Mapper
import br.com.alura.forum.models.Topic
import org.springframework.stereotype.Component

@Component
class TopicOutputMapper : Mapper<Topic, TopicOutput> {
    override fun map(item: Topic): TopicOutput {
        return TopicOutput(
            id = item.id ?: 0,
            title = item.title,
            message = item.message,
            status = item.status,
            createdAt = item.createdAt,
        )
    }
}
