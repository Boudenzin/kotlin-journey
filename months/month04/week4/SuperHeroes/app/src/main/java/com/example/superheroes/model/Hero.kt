package com.example.superheroes.model

import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int,
    val powerLevel: Int,
    @StringRes val originRes: Int,
    @StringRes val firstAppearanceRes: Int,
    @ArrayRes val abilitiesRes: Int
)
