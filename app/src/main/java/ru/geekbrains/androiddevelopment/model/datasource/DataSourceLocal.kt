package ru.geekbrains.androiddevelopment.model.datasource

import ru.geekbrains.androiddevelopment.model.data.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
