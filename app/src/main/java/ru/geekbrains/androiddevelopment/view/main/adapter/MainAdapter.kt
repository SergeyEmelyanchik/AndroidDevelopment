package geekbrains.ru.translator.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*
import ru.geekbrains.androiddevelopment.R
import ru.geekbrains.androiddevelopment.model.data.DataModel
import ru.geekbrains.androiddevelopment.utils.loadImageFromResource


class MainAdapter(private var onListItemClickListener: OnListItemClickListener) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }
    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int {
        return data.size
    }
    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = data.text
                itemView.description_textview_recycler_item.text = data.meanings?.get(0)?.translation?.translation
                itemView.image_url.loadImageFromResource(data.meanings?.get(0)?.imageUrl)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }
    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }
    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}