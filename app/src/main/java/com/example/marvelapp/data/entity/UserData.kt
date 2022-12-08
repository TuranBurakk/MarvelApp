package com.example.marvelapp.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val userName : String? = null,
    val userPhoto : String? = null,
    val favHeroList : List<FavHero> = emptyList(),
    val favComicsList: List<FavComics> = emptyList()
):Parcelable

@Parcelize
data class FavHero(
    val heroPhoto : String? =null,
    val heroName : String? =null
): Parcelable

@Parcelize
data class FavComics(
    val comicsPhoto : String? =null,
    val comicsName : String? =null,
    val comicsId : Long? = null
):Parcelable


