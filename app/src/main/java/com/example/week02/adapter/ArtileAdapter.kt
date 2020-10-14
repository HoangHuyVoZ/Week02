package com.example.week02.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week02.R
import com.example.week02.interfaces.Constant.Companion.IMAGE_URL
import com.example.week02.model.modelClass.Doc
import kotlinx.android.synthetic.main.item_articles.view.*


class ArtileAdapter : RecyclerView.Adapter<ArtileAdapter.ViewHolder>() {
    var list = ArrayList<Doc>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.title
        var image: ImageView = itemView.image
        fun bind(list: Doc) {
            title.text = list.headline.main
            if (list.multimedia.isNotEmpty()) {
                Glide.with(itemView)
                    .load(IMAGE_URL + list.multimedia[0].url)
                    .into(image)
            } else {
                Glide.with(itemView)
                    .load("https://static.tinnhanhchungkhoan.vn/2020/mobile/styles/img/no-img.png")
                    .into(image)
            }


            itemView.setOnClickListener {
                var url = list.webUrl
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(itemView.context, Uri.parse(url))
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_articles,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(items: MutableList<Doc>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }
}