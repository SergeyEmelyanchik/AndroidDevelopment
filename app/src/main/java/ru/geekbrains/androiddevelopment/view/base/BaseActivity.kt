package ru.geekbrains.androiddevelopment.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
