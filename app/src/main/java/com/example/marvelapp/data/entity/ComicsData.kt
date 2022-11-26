package com.example.marvelapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comics(
    @SerializedName("data") val comics : ComicsDoc?
):Parcelable

@Parcelize
data class ComicsDoc(
    @SerializedName("total") val total : Int?,
    @SerializedName("limit") val limit : Int?,
    @SerializedName("results") val results : List<ComicsResults>?
):Parcelable

@Parcelize
data class ComicsResults(
    @SerializedName("id") val id : Int?,
    @SerializedName("title")val title : String?,
    @SerializedName("description")val description : String?,
    @SerializedName("format") val format : String?,
    @SerializedName("urls") val comicsDetails : List<ComicsDetail>?,
    @SerializedName("thumbnail") val thumbnail : ComicsThumbnailData
):Parcelable

@Parcelize
data class ComicsDetail(
    @SerializedName("url") val detail : String?
):Parcelable

@Parcelize
data class ComicsThumbnailData(
    @SerializedName("path") val path : String?,
    @SerializedName("extension") val extension : String?
):Parcelable