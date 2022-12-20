package ru.geekbrains.core.viewModel

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSources: Boolean): T
}