package ru.geekbrains.androiddevelopment.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.androiddevelopment.view.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}