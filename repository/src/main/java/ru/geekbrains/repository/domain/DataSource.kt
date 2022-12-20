package ru.geekbrains.repository.domain


interface DataSource<T> {

    suspend fun getData(word: String): T
}
