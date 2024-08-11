package com.example.foodplus.models

import android.os.Parcelable
import android.text.Highlights

@kotlinx.parcelize.Parcelize
data class RestaurantData (
    val name : String,
    val highlights: String,
    val image: Int,
    val description: String,
    val lat: Double,
    val long: Double
) : Parcelable