package ru.geekbrains.favoritescreen.ui

import ru.geekbrains.core.viewModel.Interactor
import ru.geekbrains.model.data.AppState
import ru.geekbrains.model.data.DataModel
import ru.geekbrains.repository.domain.RepositoryLocal

class FavoriteInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(repositoryLocal.getData(word))
    }
}