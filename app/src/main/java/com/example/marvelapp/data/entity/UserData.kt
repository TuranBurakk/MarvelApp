package com.example.marvelapp.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    var userName : String? = null,
    var userPhoto : String? = null,
    var heroPhoto : String? =null,
    var heroName : String? =null,
    var comicsPhoto : String? =null,
    var comicsName : String? =null,
    var comicsId : Long? = null
):Parcelable


