package ru.geekbrains.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)
