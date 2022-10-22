package br.com.alura.forum.mappers.helpers

interface Mapper<T, U> {
    fun map(item: T): U
}