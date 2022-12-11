package ru.geekbrains.androiddevelopment.di

import androidx.room.Room
import org.koin.dsl.module
import ru.geekbrains.androiddevelopment.domain.RepositoryLocal
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.data.room.HistoryDataBase
import ru.geekbrains.androiddevelopment.model.datasource.RetrofitImplementation
import ru.geekbrains.androiddevelopment.model.datasource.RoomDataBaseImplementation
import ru.geekbrains.androiddevelopment.model.repository.Repository
import ru.geekbrains.androiddevelopment.model.repository.RepositoryImplementation
import ru.geekbrains.androiddevelopment.model.repository.RepositoryImplementationLocal
import ru.geekbrains.androiddevelopment.ui.main.MainInteractor
import ru.geekbrains.androiddevelopment.ui.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}