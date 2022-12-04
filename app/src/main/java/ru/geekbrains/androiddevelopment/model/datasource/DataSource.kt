package ru.geekbrains.androiddevelopment.model.datasource


interface DataSource<T> {

    suspend fun getData(word: String): T
}
