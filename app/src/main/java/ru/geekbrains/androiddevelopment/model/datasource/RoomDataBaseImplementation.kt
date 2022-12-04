package ru.geekbrains.androiddevelopment.model.datasource

import io.reactivex.Observable
import ru.geekbrains.androiddevelopment.model.data.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
