package ru.geekbrains.androiddevelopment.model.repository

interface Repository<T> {

    suspend fun getData(word: String): T
}
