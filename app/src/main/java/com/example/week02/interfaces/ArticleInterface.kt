package com.example.week02.interfaces

import com.example.week02.model.modelClass.Doc


interface ArticleInterface {
    interface ArticleModel {
        fun getDataFormPresenter()
        fun getFilterFromPresenter(sort: String, fq: String, bengindate: String)
        fun getSearchFromPresenter(search: String)
        fun getPageFromPresenter(pastVisibleItem: Int, total: Int)
    }

    interface ArticleView {
        fun onSuccess(list: List<Doc>)
        fun onFailed(msg: String)
//        fun onSearchSuccess(list: List<Doc>)
//        fun onSearchFailer(msg: String)
//        fun onPageSuccess()
//        fun onPageFailer(msg: String)
    }

    interface ArticlePresenter {
        fun networkCall()
        fun filterCall(sort: String, fq: String, bengindate: String)
        fun SearchCall(search: String)
        fun pageCall(pastVisibleItem: Int, total: Int)
        fun onDestroy()
    }
}