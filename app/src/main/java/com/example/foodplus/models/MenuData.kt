package com.example.foodplus.models

import android.media.Image
import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data  class MenuData (
    val name : String,
    val stock : Int,
    val price : Int,
    val image: Int,
    val normalPrice : Int,
    val description : String,
    val expireDate : String
) : Parcelable