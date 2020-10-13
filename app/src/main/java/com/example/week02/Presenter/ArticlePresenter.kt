package com.example.week02.Presenter

import com.example.week02.Interfaces.ArticleInterface
import com.example.week02.Model.ModelClass.Doc
import com.example.week02.Model.Repos.ArticleRepos

class ArticlePresenter(articleVew: ArticleInterface.ArticleView) : ArticleInterface.ArticlePresenter{
    private var model: ArticleInterface.ArticleModel = ArticleRepos()
    private var view: ArticleInterface.ArticleView = articleVew

    override fun networkCall(api: String, page: Int, begin_date: String, sort: String, q: String) {
        model.getDataFormPresenter(api,page,begin_date,sort,q, this)
    }

    override fun showArticle(): ArrayList<Doc>? = model.getArticle()

    override fun uiAutoUpdate() {
        view.updateViewData()
    }
}