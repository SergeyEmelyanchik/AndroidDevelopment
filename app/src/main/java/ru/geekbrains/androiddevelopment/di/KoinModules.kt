package ru.geekbrains.androiddevelopment.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.datasource.RetrofitImplementation
import ru.geekbrains.androiddevelopment.model.datasource.RoomDataBaseImplementation
import ru.geekbrains.androiddevelopment.model.repository.Repository
import ru.geekbrains.androiddevelopment.model.repository.RepositoryImplementation
import ru.geekbrains.androiddevelopment.ui.main.MainInteractor
import ru.geekbrains.androiddevelopment.view.main.MainViewModel

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}