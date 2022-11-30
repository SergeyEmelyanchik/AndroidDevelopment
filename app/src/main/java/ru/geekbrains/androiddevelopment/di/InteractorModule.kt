package ru.geekbrains.androiddevelopment.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.repository.Repository
import ru.geekbrains.androiddevelopment.view.main.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}