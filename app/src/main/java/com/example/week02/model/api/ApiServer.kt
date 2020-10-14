package com.example.week02.model.api

import com.example.week02.interfaces.Constant.Companion.API_KEY
import com.example.week02.model.modelClass.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    //    @GET("articlesearch.json")
//    fun getListArticles(
//        @Query("api-key") api_key: String= API_KEY,
//        @Query("page")page : Int= 0,
//        @Query("q")q: String = "",
//    ) : Call<Articles>
    @GET("articlesearch.json")
    fun getListArticles(
        @Query("api-key") api_key: String = API_KEY,
        @Query("page") page: Int = 0,
        @Query("begin_date") begin_date: String = "",
        @Query("sort") sort: String = "",
        @Query("q") q: String = "",
        @Query("fq") fq: String = ""
    ): Call<Articles>
}