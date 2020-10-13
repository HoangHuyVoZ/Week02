package com.example.week02.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week02.Interfaces.Constant.Companion.IMAGE_URL
import com.example.week02.Model.ModelClass.Doc
import com.example.week02.R
import kotlinx.android.synthetic.main.item_articles.view.*

class ArtileAdapter( var context: Context) : RecyclerView.Adapter<ArtileAdapter.ViewHolder>(){
    var list = ArrayList<Doc>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.title
        var image: ImageView= itemView.image
        fun bind(list: Doc) {
            title.text = list?.headline?.main
            Glide.with(itemView.context).load("${IMAGE_URL}${list?.multimedia?.get(0)?.url}").into(image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_articles,parent,false)
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