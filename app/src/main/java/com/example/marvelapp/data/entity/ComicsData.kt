package com.example.marvelapp.data.entity

import com.google.gson.annotations.SerializedName

data class Comics(
    @SerializedName("data") val comics : ComicsDoc?
)

data class ComicsDoc(
    @SerializedName("total") val total : Int?,
    @SerializedName("limit") val limit : Int?,
    @SerializedName("results") val results : List<ComicsResults>?
)

data class ComicsResults(
    @SerializedName("id") val id : Int?,
    @SerializedName("title")val title : String?,
    @SerializedName("description")val description : String?,
    @SerializedName("format") val format : String?,
    @SerializedName("urls") val comicsDetails : List<ComicsDetail>?,
    @SerializedName("thumbnail") val thumbnail : ComicsThumbnailData
)

data class ComicsDetail(
    @SerializedName("url") val detail : String?
)
data class ComicsThumbnailData(
    @SerializedName("path") val path : String?,
    @SerializedName("extension") val extension : String?
)