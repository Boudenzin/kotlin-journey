package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int,
    val powerLevel: Int,           // Escala de 1 a 100
    val origin: String,            // Lugar de origem
    val firstAppearance: String,   // Ano ou HQ de estreia
    val abilities: List<String>    // Lista de poderes
)
