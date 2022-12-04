package ru.geekbrains.androiddevelopment.view.main

import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.repository.Repository
import ru.geekbrains.androiddevelopment.presenter.Interactor

class MainInteractor(
    val remoteRepo: Repository<List<DataModel>>,
    val localRepo: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepo
            } else {
                localRepo
            }.getData(word)
        )
    }
}