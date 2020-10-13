package com.example.week02.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week02.Adapter.ArtileAdapter
import com.example.week02.Interfaces.ArticleInterface
import com.example.week02.Interfaces.Constant.Companion.API_KEY
import com.example.week02.Model.ModelClass.Doc
import com.example.week02.Presenter.ArticlePresenter
import com.example.week02.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ArticleInterface.ArticleView{
    lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: ArtileAdapter
    var presenter: ArticlePresenter? =null
    var page : Int = 1
    var sort :String=""
    var begin_date : String= "20120101"
    var q: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        presenter?.networkCall(API_KEY,page,begin_date,sort,q)

    }

    private fun initUI() {
        layoutManager = GridLayoutManager(this, 3)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        adapter = ArtileAdapter(this)
        recyclerView.adapter = adapter

    }

    override fun updateViewData() {
        adapter.addList(presenter?.showArticle() as ArrayList<Doc>)
        adapter.notifyDataSetChanged()
    }
}