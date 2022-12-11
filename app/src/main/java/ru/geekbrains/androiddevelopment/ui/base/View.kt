package ru.geekbrains.androiddevelopment.ui.base

import ru.geekbrains.androiddevelopment.model.data.AppState


interface View {
    fun renderData(appState: AppState)
}