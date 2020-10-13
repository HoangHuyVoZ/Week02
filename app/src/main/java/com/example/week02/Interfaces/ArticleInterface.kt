package com.example.week02.Interfaces

import com.example.week02.Model.ModelClass.Doc

interface ArticleInterface {
     interface ArticleModel {
        fun getDataFormPresenter(api: String, page: Int, begin_date: String, sort: String, q: String,presenter: ArticlePresenter)
        fun getArticle(): ArrayList<Doc>?
    }

    interface ArticleView {
        fun updateViewData()
    }

    interface ArticlePresenter {
        fun networkCall(api: String, page: Int, begin_date: String, sort: String, q: String)
        fun showArticle(): ArrayList<Doc>?
        fun uiAutoUpdate()
    }
}