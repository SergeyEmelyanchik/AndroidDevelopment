package ru.geekbrains.favoritescreen.utils

import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import ru.geekbrains.favoritescreen.R

fun AppCompatImageView.loadImageFromResource(imageUrl: String?) {
    imageUrl?.let {
        Picasso
            .get()
            .load("https:$it")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(this)
    }

}