package com.example.week02.Model.ModelClass


import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("status")
    val status: String
)