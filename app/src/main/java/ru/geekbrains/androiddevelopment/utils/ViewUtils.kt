package ru.geekbrains.androiddevelopment.utils

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso

fun AppCompatImageView.loadImageFromResource(imageUrl: String?) {
    imageUrl?.let {
        Picasso
            .get()
            .load("https:$it")
            .into(this)
    }

}