package ru.geekbrains.repository.domain

import ru.geekbrains.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}