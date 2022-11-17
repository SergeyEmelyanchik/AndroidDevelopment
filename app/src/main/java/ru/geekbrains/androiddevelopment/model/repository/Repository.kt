package ru.geekbrains.androiddevelopment.model.repository

import io.reactivex.Observable

interface Repository<T> {

    fun getData(word: String): Observable<T>
}
