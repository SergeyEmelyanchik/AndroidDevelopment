package ru.geekbrains.androiddevelopment.view.base

import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {
    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}
