package ru.geekbrains.androiddevelopment.model.datasource

import ru.geekbrains.androiddevelopment.domain.DataSourceLocal
import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.data.room.HistoryDao
import ru.geekbrains.androiddevelopment.network.convertDataModelSuccessToEntity
import ru.geekbrains.androiddevelopment.network.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}
