package com.example.week02.presenter

import com.example.week02.interfaces.ArticleInterface
import com.example.week02.model.modelClass.Doc
import com.example.week02.model.repos.ArticleRepos

class ArticlePresenter(articleVew: ArticleInterface.ArticleView) :
    ArticleInterface.ArticlePresenter {
    private var model: ArticleInterface.ArticleModel = ArticleRepos(
        this::onSuccess, this::onFailed,
//        this::onPageSuccess,this::onPageFailer,
//        this::onSearchSuccess,this::onSearchFailed)
    )
    private var view: ArticleInterface.ArticleView? = articleVew
    override fun networkCall() {
        model.getDataFormPresenter()
    }

    override fun filterCall(sort: String, fq: String, bengindate: String) {
        model.getFilterFromPresenter(sort, fq, bengindate)
    }

    override fun SearchCall(search: String) {
        model.getSearchFromPresenter(search)
    }

    override fun pageCall(pastVisibleItem: Int, total: Int) {
        model.getPageFromPresenter(pastVisibleItem,total)
    }


    fun onSuccess(list: List<Doc>?) {
        if (list != null) {
            view?.onSuccess(list)
        }
    }

    fun onFailed(msg: String?) {
        if (msg != null) {
            view?.onFailed(msg)
        }
    }

    override fun onDestroy() {
        view = null
    }
//    fun onSearchSuccess(list: List<Doc>?) {
//        if (list != null) {
//            view?.onSearchSuccess(list)
//        }
//    }
//
//    fun onSearchFailed(msg: String?) {
//        if (msg != null) {
//            view?.onSearchFailer(msg)
//        }
//    }

//
//    private fun onPageFailer(msg: String?) {
//        if (msg != null) {
//            view?.onPageFailer(msg)
//        }
//    }
//
//    private fun onPageSuccess() {
//        view?.onPageSuccess()
//    }

}