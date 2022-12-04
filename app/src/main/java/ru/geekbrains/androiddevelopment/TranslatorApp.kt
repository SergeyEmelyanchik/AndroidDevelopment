package ru.geekbrains.androiddevelopment

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import ru.geekbrains.androiddevelopment.di.application
import ru.geekbrains.androiddevelopment.di.mainScreen


class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}