package br.com.alura.forum.models

import javax.persistence.*

@Entity
data class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val email: String,

    val password: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_role")
    val role: List<Role> = mutableListOf()
)
