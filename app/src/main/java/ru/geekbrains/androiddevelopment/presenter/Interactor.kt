package ru.geekbrains.androiddevelopment.presenter

interface Interactor<T> {

    suspend fun getData(word: String, fromRemoteSources: Boolean): T
}