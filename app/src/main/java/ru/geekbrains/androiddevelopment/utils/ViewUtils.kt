package ru.geekbrains.androiddevelopment.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import ru.geekbrains.androiddevelopment.R

fun AppCompatImageView.loadImageFromResource(imageUrl: String?) {
    imageUrl?.let {
        Picasso
            .get()
            .load("https:$it")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(this)
    }

}


fun ImageView.usePicassoToLoadPhoto(imageLink: String, view: SwipeRefreshLayout) {
    Picasso.get().load("https:$imageLink")
        .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
        .into(this@usePicassoToLoadPhoto, object : Callback {
            override fun onSuccess() {
                stopRefreshAnimationIfNeeded(view)
            }

            override fun onError(e: Exception?) {
                stopRefreshAnimationIfNeeded(view)
                this@usePicassoToLoadPhoto.setImageResource(R.drawable.ic_load_error_vector)
            }
        })
}

fun stopRefreshAnimationIfNeeded(view: SwipeRefreshLayout) {
    if (view.isRefreshing) {
        view.isRefreshing = false
    }
}