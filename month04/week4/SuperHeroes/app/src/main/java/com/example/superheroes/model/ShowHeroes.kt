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
                origin = "Neo City",
                firstAppearance = "Crônicas do Jetpack #1 (2018)",
                abilities = listOf("Voo", "Super Velocidade", "Manobras Aéreas")
            ),
            Hero(
                nameRes = R.string.hero2,
                descriptionRes = R.string.description2,
                imageRes = R.drawable.android_superhero2,
                powerLevel = 92,
                origin = "Dimensão Paralela Z-12",
                firstAppearance = "Reality Files #3 (2019)",
                abilities = listOf("Controle de realidades", "Visão da verdade absoluta", "Telepatia limitada")

            ),
            Hero(
                nameRes = R.string.hero3,
                descriptionRes = R.string.description3,
                imageRes = R.drawable.android_superhero3,
                powerLevel = 78,
                origin = "Floresta da Penumbra",
                firstAppearance = "Legends of Shadows #7 (2016)",
                abilities = listOf("Camuflagem total", "Força ampliada à noite")

            ),
            Hero(
                nameRes = R.string.hero4,
                descriptionRes = R.string.description4,
                imageRes = R.drawable.android_superhero4,
                powerLevel = 88,
                origin = "Montanhas de Canária",
                firstAppearance = "Bravery Saga #2 (2020)",
                abilities = listOf("Canto inspirador", "Escudo sonoro", "Força sobre-humana")

            ),
            Hero(
                nameRes = R.string.hero5,
                descriptionRes = R.string.description5,
                imageRes = R.drawable.android_superhero5,
                powerLevel = 81,
                origin = "Arquipélago Maru",
                firstAppearance = "Sky Rescue #5 (2017)",
                abilities = listOf("Planar longas distâncias", "Agilidade felina")

            ),
            Hero(
                nameRes = R.string.hero6,
                descriptionRes = R.string.description6,
                imageRes = R.drawable.android_superhero6,
                powerLevel = 95,
                origin = "Laboratório Secreto Y-9",
                firstAppearance = "Dynamic Shift #1 (2021)",
                abilities = listOf("Metamorfose", "Regeneração rápida", "Energia vital ilimitada")

            )
        )
    }
}