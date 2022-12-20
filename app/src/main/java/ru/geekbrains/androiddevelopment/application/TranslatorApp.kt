package ru.geekbrains.androiddevelopment.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.geekbrains.androiddevelopment.di.application
import ru.geekbrains.androiddevelopment.di.favoriteScreen
import ru.geekbrains.androiddevelopment.di.mainScreen


class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, favoriteScreen))
        }
    }
}