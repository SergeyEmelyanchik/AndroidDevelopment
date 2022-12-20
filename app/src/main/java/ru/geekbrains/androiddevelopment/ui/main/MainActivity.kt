package ru.geekbrains.androiddevelopment.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import geekbrains.ru.translator.view.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.androiddevelopment.R
import ru.geekbrains.model.data.AppState
import ru.geekbrains.androiddevelopment.network.convertMeaningsToString
import ru.geekbrains.androiddevelopment.network.isOnline
import ru.geekbrains.androiddevelopment.ui.description.DescriptionActivity
import ru.geekbrains.androiddevelopment.view.main.SearchDialogFragment
import ru.geekbrains.core.base.BaseActivity
import ru.geekbrains.favoritescreen.ui.FavoriteActivity


class MainActivity : BaseActivity<AppState, MainInteractor>() {
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: ru.geekbrains.model.data.DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniViewModel()
        initViews()
    }

    private fun iniViewModel() {
        if (main_activity_recyclerview.adapter != null) {
            throw IllegalStateException(getString(R.string.error_initialised))
        }

        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.getData("", false)
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        search_fab.setOnClickListener(fabClickListener)
        main_activity_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        main_activity_recyclerview.adapter = adapter

        val itemDecoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical, null)!!
        )
        main_activity_recyclerview.addItemDecoration(itemDecoration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }

    override fun setDataToAdapter(data: List<ru.geekbrains.model.data.DataModel>) {
        adapter.setData(data)
    }
}