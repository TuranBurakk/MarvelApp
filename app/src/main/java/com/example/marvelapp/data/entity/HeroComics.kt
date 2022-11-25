package com.example.marvelapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroComics(
    @SerializedName("data") val HeroComics : HeroComicsData?
):Parcelable

@Parcelize
data class HeroComicsData(
    @SerializedName("results") val HeroComicsResult : List<HeroComicsResultData>?
):Parcelable

@Parcelize
data class HeroComicsResultData(
    @SerializedName("title") val title : String?,
    @SerializedName("thumbnail") val images : HeroComicsImage?
):Parcelable

@Parcelize
data class HeroComicsImage(
    @SerializedName("path") val path : String?,
    @SerializedName("extension") val extension : String?
):Parcelable