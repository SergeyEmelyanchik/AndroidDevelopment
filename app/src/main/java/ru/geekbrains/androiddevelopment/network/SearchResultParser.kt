package ru.geekbrains.androiddevelopment.network

import ru.geekbrains.androiddevelopment.model.data.AppState
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.model.data.Meanings
import ru.geekbrains.androiddevelopment.model.data.Translation
import ru.geekbrains.androiddevelopment.model.data.room.HistoryEntity

fun parseSearchResults(data: AppState): AppState {
    val newSearchResults = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
        else -> {}
    }

    return AppState.Success(newSearchResults)
}

private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.id, meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.id, dataModel.text, newMeanings))
        }
    }
}

fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}

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