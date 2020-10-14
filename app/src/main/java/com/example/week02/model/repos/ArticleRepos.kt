package com.example.week02.model.repos

import com.example.week02.interfaces.ArticleInterface
import com.example.week02.interfaces.Constant.Companion.API_KEY
import com.example.week02.model.api.API
import com.example.week02.model.api.ApiServer
import com.example.week02.model.modelClass.Articles
import com.example.week02.model.modelClass.Doc
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepos(
    private val onGetDataSuccess: (list: List<Doc>?) -> Unit,
    private val onGetDataFailed: (msg: String?) -> Unit,
//    private val onGetPageSuccess: () -> Unit,
//    private val onGetPageFailed: (msg: String?) -> Unit,
//    private val onSearchSuccess: (list: List<Doc>?) -> Unit,
//    private val onSearchFailer: (msg: String?) -> Unit,

) : ArticleInterface.ArticleModel {
    var api = API_KEY
    var page = 0
    var begin_date = "20120101"
    var sort = ""
    var q = ""
    var fq = ""

    override fun getDataFormPresenter(

    ) {
        callAPi()
    }

    override fun getFilterFromPresenter(sort1: String, fq1: String, bengindate1: String) {
        sort = sort1
        fq = fq1
        begin_date = bengindate1
        page = 0
        q = ""
        callAPi()
    }

    override fun getSearchFromPresenter(search: String) {
        q = search
        page = 0
        sort = ""
        fq = ""
        begin_date = "20120101"
        callAPi()
    }

    override fun getPageFromPresenter(pastVisibleItem: Int, total: Int) {
        if (pastVisibleItem == total - 1) {
            page++
            callAPi()
        }
    }

    private fun callAPi() {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getListArticles(api, page, begin_date, sort, q, fq)

        call.enqueue(object : Callback<Articles> {
            override fun onFailure(call: Call<Articles>, t: Throwable) {
                onGetDataFailed.invoke(t.message)
            }

            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                if (response.isSuccessful) {
                    var list = response.body()?.response?.docs
                    if (list != null) {
                        onGetDataSuccess.invoke(list)
                    }
                }
            }
        })
    }


}