package com.example.marvelapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersData(
    @SerializedName("data") val characters : Characters?
):Parcelable

@Parcelize
data class Characters(
    @SerializedName("total") val total : Int?,
    @SerializedName("limit") val limit : Int?,
    @SerializedName("results") val results : List<ResultsData>?
):Parcelable

@Parcelize
data class ResultsData(
    @SerializedName("id")val id : Long?,
    @SerializedName("name")val name : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("thumbnail")val image : ImageData?,
    @SerializedName("comics")val comics : ComicsData?,
    @SerializedName("series")val series : SeriesData?,
    @SerializedName("stories")val stories :StoriesData?,
    @SerializedName("urls") val detail : List<Details>?,
    var isFavorite : Boolean = false
):Parcelable

@Parcelize
data class ImageData(
    @SerializedName("path")val path : String?,
    @SerializedName("extension")val extension : String?
):Parcelable

@Parcelize
data class ComicsData(
    @SerializedName("collectionURI")val collectionURI : String?,
    @SerializedName("items")val comicsItems : List<ItemsData>?
):Parcelable

@Parcelize
data class ItemsData(
    @SerializedName("resourceURI")val resourceURI : String?,
    @SerializedName("name")val name : String?
):Parcelable

@Parcelize
data class SeriesData(
    @SerializedName("items")val items : List<ItemsData>?
):Parcelable

@Parcelize
data class StoriesData(
    @SerializedName("items")val items : List<ItemsData>?
):Parcelable

@Parcelize
data class Details(
    @SerializedName("url")val url : String?
):Parcelable