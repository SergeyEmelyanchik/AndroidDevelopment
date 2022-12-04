package ru.geekbrains.androiddevelopment.view.main

import io.reactivex.Observable
import ru.geekbrains.androiddevelopment.di.NAME_LOCAL
import ru.geekbrains.androiddevelopment.di.NAME_REMOTE
import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.repository.Repository
import ru.geekbrains.androiddevelopment.presenter.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor(
    val remoteRepo: Repository<List<DataModel>>,
    val localRepo: Repository<List<DataModel>>,
) : Interactor<AppState> {
    override fun getData(word: String, fromRemoteSources: Boolean): Observable<AppState> {
        return if (fromRemoteSources) {
            remoteRepo.getData(word).map { AppState.Success(it) }
        } else {
            localRepo.getData(word).map { AppState.Success(it) }
        }
    }
}