package com.example.challenge_chapter6_fix.model


import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("created_by")
    var createdBy: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("favorite_count")
    var favoriteCount: Int?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("iso_639_1")
    var iso6391: String?,
    @SerializedName("item_count")
    var itemCount: Int?,
    @SerializedName("items")
    var items: List<Item?>?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("poster_path")
    var posterPath: String?
)