package br.com.alura.forum.controllers

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.dto.input.UpdateTopicInput
import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.services.TopicService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val service: TopicService,
) {
    @GetMapping
    fun listAll(): List<TopicOutput> {
        return service.listAll()
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long,
    ): TopicOutput = service.findById(id)

    @PostMapping
    fun register(
        @RequestBody @Valid dto: NewTopicInput,
    ) {
        service.register(dto)
    }

    @PutMapping
    fun update(
        @RequestBody @Valid dto: UpdateTopicInput
    ) {
        service.update(dto)
    }
}
