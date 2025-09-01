package com.example.superheroes.model

import com.example.superheroes.R

class ShowHeroes {
    object HeroesRepository {
        val heroes = listOf(
            Hero(
                nameRes = R.string.hero1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.android_superhero1,
                powerLevel = 85,
                originRes = R.string.hero1_origin,
                firstAppearanceRes = R.string.hero1_first_appearance,
                abilitiesRes = R.array.hero1_abilities
            ),
            Hero(
                nameRes = R.string.hero2,
                descriptionRes = R.string.description2,
                imageRes = R.drawable.android_superhero2,
                powerLevel = 92,
                originRes = R.string.hero2_origin,
                firstAppearanceRes = R.string.hero2_first_appearance,
                abilitiesRes = R.array.hero2_abilities
            ),
            Hero(
                nameRes = R.string.hero3,
                descriptionRes = R.string.description3,
                imageRes = R.drawable.android_superhero3,
                powerLevel = 78,
                originRes = R.string.hero3_origin,
                firstAppearanceRes = R.string.hero3_first_appearance,
                abilitiesRes = R.array.hero3_abilities
            ),
            Hero(
                nameRes = R.string.hero4,
                descriptionRes = R.string.description4,
                imageRes = R.drawable.android_superhero4,
                powerLevel = 88,
                originRes = R.string.hero4_origin,
                firstAppearanceRes = R.string.hero4_first_appearance,
                abilitiesRes = R.array.hero4_abilities
            ),
            Hero(
                nameRes = R.string.hero5,
                descriptionRes = R.string.description5,
                imageRes = R.drawable.android_superhero5,
                powerLevel = 81,
                originRes = R.string.hero5_origin,
                firstAppearanceRes = R.string.hero5_first_appearance,
                abilitiesRes = R.array.hero5_abilities
            ),
            Hero(
                nameRes = R.string.hero6,
                descriptionRes = R.string.description6,
                imageRes = R.drawable.android_superhero6,
                powerLevel = 95,
                originRes = R.string.hero6_origin,
                firstAppearanceRes = R.string.hero6_first_appearance,
                abilitiesRes = R.array.hero6_abilities
            )
        )
    }
}