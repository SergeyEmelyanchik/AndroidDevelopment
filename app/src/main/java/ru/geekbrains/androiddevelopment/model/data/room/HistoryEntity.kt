package ru.geekbrains.androiddevelopment.model.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,
    @field:ColumnInfo(name = "word")
    var word: String,
    @field:ColumnInfo(name = "description")
    var description: String?,
    @field:ColumnInfo(name = "imageUrl")
    var imageUrl: String?
)