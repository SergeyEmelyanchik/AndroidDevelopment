package ru.geekbrains.favoritescreen.ui

import FavoriteAdapter
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.core.base.BaseActivity
import ru.geekbrains.favoritescreen.R
import ru.geekbrains.model.data.AppState
import ru.geekbrains.model.data.DataModel

class FavoriteActivity : BaseActivity<AppState, FavoriteInteractor>() {
    override lateinit var model: FavoriteViewModel
    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter(onListItemClickListener) }

    private val onListItemClickListener: FavoriteAdapter.OnListItemClickListener =
        object : FavoriteAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
//                startActivity(
//                    DescriptionActivity.getIntent(
//                        this@FavoriteActivity,
//                        data.text!!,
//                        convertMeaningsToString(data.meanings!!),
//                        data.meanings[0].imageUrl
//                    )
//                )
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        iniViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    private fun iniViewModel() {
        if (favorite_activity_recyclerview.adapter != null) {
            throw IllegalStateException(getString(R.string.error_initialised))
        }
        val viewModel: FavoriteViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@FavoriteActivity, { renderData(it) })
    }

    private fun initViews() {
        favorite_activity_recyclerview.adapter = adapter

        val itemDecoration = DividerItemDecoration(this@FavoriteActivity, GridLayoutManager.VERTICAL)
        itemDecoration.setDrawable(
            ResourcesCompat.getDrawable(resources, R.drawable.separator_vertical, null)!!
        )
        favorite_activity_recyclerview.addItemDecoration(itemDecoration)
    }
}