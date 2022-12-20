package ru.geekbrains.repository.domain

interface Repository<T> {

    suspend fun getData(word: String): T
}
