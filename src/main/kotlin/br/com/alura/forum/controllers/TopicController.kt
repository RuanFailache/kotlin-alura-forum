package br.com.alura.forum.controllers

import br.com.alura.forum.dto.input.NewTopicInput
import br.com.alura.forum.dto.input.UpdateTopicInput
import br.com.alura.forum.dto.output.TopicOutput
import br.com.alura.forum.services.TopicService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val service: TopicService,
) {
    @GetMapping
    @Cacheable("topics")
    fun listAll(
        @RequestParam(required = false) courseName: String?,
        page: Pageable
    ): ResponseEntity<Page<TopicOutput>> {
        val topicList: Page<TopicOutput> = service.listAll(
            courseName,
            page
        )
        return ResponseEntity.ok(topicList)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable id: Long,
    ): TopicOutput = service.findById(id)

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun register(
        @RequestBody @Valid dto: NewTopicInput,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<TopicOutput> {
        val newTopic: TopicOutput = service.register(dto)
        val uri = uriBuilder.path("/topics/${newTopic.id}").build().toUri()
        return ResponseEntity.created(uri).body(newTopic)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(
        @RequestBody @Valid dto: UpdateTopicInput
    ): ResponseEntity<TopicOutput> {
        val updatedTopic = service.update(dto)
        return ResponseEntity.ok(updatedTopic)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun delete(
        @PathVariable id: Long
    ) {
        service.delete(id)
    }
}
