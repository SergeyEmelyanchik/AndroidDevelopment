package ru.geekbrains.androiddevelopment.model.datasource

import ru.geekbrains.androiddevelopment.model.data.DataModel

class DataSourceRemote(private val remoteProvider: RetrofitImplementation = RetrofitImplementation()) :
    DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> = remoteProvider.getData(word)
}
