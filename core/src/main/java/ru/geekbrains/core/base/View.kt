package ru.geekbrains.core.base

import ru.geekbrains.model.data.AppState


interface View {
    fun renderData(appState: AppState)
}