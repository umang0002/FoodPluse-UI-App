package com.example.foodplus.models

import android.content.ClipDescription
import androidx.annotation.DrawableRes

data class OnBoardingData(
    val title : String,
    val description: String,
    @DrawableRes val image: Int
)
