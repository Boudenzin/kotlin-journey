package com.example.desconecta30.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val dayNumberResId: Int,
    @StringRes val weekTitleResId: Int,
    @StringRes val tipResId: Int,
    @DrawableRes val imageResId: Int
)
