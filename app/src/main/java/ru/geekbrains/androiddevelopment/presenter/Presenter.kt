package ru.geekbrains.androiddevelopment.presenter

import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)
}
