package ru.geekbrains.androiddevelopment.model.datasource

import io.reactivex.Observable
import ru.geekbrains.androiddevelopment.model.data.DataModel

class DataSourceLocal(private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()) :
    DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}
