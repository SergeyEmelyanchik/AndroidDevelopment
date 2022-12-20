package ru.geekbrains.repository.utils

import ru.geekbrains.model.data.AppState
import ru.geekbrains.model.data.DataModel
import ru.geekbrains.model.data.Meanings
import ru.geekbrains.model.data.Translation
import ru.geekbrains.repository.room.HistoryEntity

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].id!!, searchResult[0].text!!, "", "")
                if (searchResult[0].meanings.isNullOrEmpty()) {
                    HistoryEntity(searchResult[0].id!!, searchResult[0].text!!, "", "")
                } else {
                    searchResult[0].meanings?.get(0)?.let {
                        val descriptions = it.translation!!.translation
                        val imageUrl = it.imageUrl.toString()
                        HistoryEntity(
                            searchResult[0].id!!,
                            searchResult[0].text!!,
                            descriptions,
                            imageUrl
                        )
                    }
                }
            }
        }
        else -> null
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<DataModel> {
    val searchResult = ArrayList<DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(
                DataModel(
                    entity.id, entity.word,
                    listOf(Meanings(entity.id, Translation(entity.description), entity.imageUrl))
                )
            )
        }
    }
    return searchResult
}