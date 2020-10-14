package com.example.week02.model.modelClass


import com.google.gson.annotations.SerializedName

data class Byline(
    @SerializedName("organization")
    val organization: Any,
    @SerializedName("original")
    val original: String,
    @SerializedName("person")
    val person: List<Person>
)