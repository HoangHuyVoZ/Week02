package com.example.week02.Model.Repos

import android.util.Log
import android.widget.Toast
import com.example.week02.Interfaces.ArticleInterface
import com.example.week02.Model.API.API
import com.example.week02.Model.API.ApiServer
import com.example.week02.Model.ModelClass.Articles
import com.example.week02.Model.ModelClass.Doc
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepos : ArticleInterface.ArticleModel {

     var result: ArrayList<Doc>? = null




    override fun getDataFormPresenter(api: String, page: Int, begin_date: String, sort: String, q: String, presenter: ArticleInterface.ArticlePresenter
    ) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getListArticles(api, page, begin_date, sort, q)

        call.enqueue(object : Callback<Articles> {
            override fun onFailure(call: Call<Articles>, t: Throwable) {
                Log.d("failure", t.toString())
            }

            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                if (response.isSuccessful) {
                    var list = response.body()?.response?.docs
                    if (list != null) {
                        result = list as ArrayList<Doc>?
                    }
                    presenter.uiAutoUpdate()


                }


            }


        })
    }


    override fun getArticle(): ArrayList<Doc>? = result
}