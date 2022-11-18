package com.example.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

data class CharactersData(
    @SerializedName("data") val characters : Characters?
)

data class Characters(
    @SerializedName("limit") val limit : Int?,
    @SerializedName("results") val results : List<ResultsData>?
)

data class ResultsData(
    @SerializedName("id")val id : Long?,
    @SerializedName("name")val name : String?,
    @SerializedName("thumbnail")val image : ImageData?,
    @SerializedName("comics")val comics : ComicsData?,
    @SerializedName("series")val series : SeriesData?,
    @SerializedName("stories")val stories :StoriesData?,
    @SerializedName("urls") val detail : List<Details>?
)

data class ImageData(
    @SerializedName("path")val path : String?,
    @SerializedName("extension")val extension : String?
)

data class ComicsData(
    @SerializedName("collectionURI")val collectionURI : String?,
    @SerializedName("items")val comicsItems : List<ItemsData>?
)
data class ItemsData(
    @SerializedName("resourceURI")val resourceURI : String?,
    @SerializedName("name")val name : String?
)
data class SeriesData(
    @SerializedName("items")val items : List<ItemsData>?
)
data class StoriesData(
    @SerializedName("items")val items : List<ItemsData>?
)
data class Details(
    @SerializedName("url")val url : String?
)