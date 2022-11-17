package ru.geekbrains.androiddevelopment.model.datasource

import io.reactivex.Observable

interface DataSource<T> {

    fun getData(word: String): Observable<T>
}
