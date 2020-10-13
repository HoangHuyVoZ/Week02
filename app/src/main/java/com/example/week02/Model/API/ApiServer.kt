package com.example.week02.Model.API

import com.example.week02.Interfaces.Constant.Companion.API_KEY
import com.example.week02.Model.ModelClass.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    @GET("articlesearch.json")
    fun getListArticles(
        @Query("api-key") api_key: String= API_KEY,
        @Query("page")page : Int= 1,
        @Query("begin_date")begin_date: String ="20120101",
        @Query("sort") sort: String = "",
        @Query("q")q: String = ""
    ) : Call<Articles>
}