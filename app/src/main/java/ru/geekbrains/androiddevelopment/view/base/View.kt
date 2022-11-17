package ru.geekbrains.androiddevelopment.view.base

import ru.geekbrains.androiddevelopment.model.data.AppState


interface View {
    fun renderData(appState: AppState)
}