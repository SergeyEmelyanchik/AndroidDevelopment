package ru.geekbrains.androiddevelopment.di

import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.repository.room.HistoryDataBase
import ru.geekbrains.repository.datasource.RetrofitImplementation
import ru.geekbrains.repository.RepositoryImplementation
import ru.geekbrains.repository.RepositoryImplementationLocal
import ru.geekbrains.androiddevelopment.ui.main.MainInteractor
import ru.geekbrains.androiddevelopment.ui.main.MainViewModel
import ru.geekbrains.favoritescreen.ui.FavoriteInteractor
import ru.geekbrains.favoritescreen.ui.FavoriteViewModel
import ru.geekbrains.repository.datasource.RoomDataBaseImplementation

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<ru.geekbrains.repository.domain.Repository<List<ru.geekbrains.model.data.DataModel>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<ru.geekbrains.repository.domain.RepositoryLocal<List<ru.geekbrains.model.data.DataModel>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(
                get()
            )
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val favoriteScreen = module {
    factory { FavoriteViewModel(get()) }
    factory { FavoriteInteractor(get()) }
}