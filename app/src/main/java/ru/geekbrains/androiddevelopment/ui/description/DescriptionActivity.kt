package ru.geekbrains.androiddevelopment.ui.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_description.*
import ru.geekbrains.androiddevelopment.R
import ru.geekbrains.utils.ui.AlertDialogFragment
import ru.geekbrains.androiddevelopment.network.isOnline
import ru.geekbrains.androiddevelopment.utils.stopRefreshAnimationIfNeeded
import ru.geekbrains.androiddevelopment.utils.usePicassoToLoadPhoto

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        setActionbarHomeButtonAsUp()
        description_screen_swipe_refresh_layout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setData() {
        val bundle = intent.extras
        description_header.text = bundle?.getString(WORD_EXTRA)
        description_textview.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded(description_screen_swipe_refresh_layout)
        } else {
            description_imageview.usePicassoToLoadPhoto(
                imageLink,
                description_screen_swipe_refresh_layout
            )
            //description_imageview.useGlideToLoadPhoto(imageLink,description_screen_swipe_refresh_layout)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            setData()
        } else {
            ru.geekbrains.utils.ui.AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded(description_screen_swipe_refresh_layout)
        }
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"
        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }
}