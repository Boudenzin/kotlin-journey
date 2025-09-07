package com.example.desconecta30.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.desconecta30.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
    Font(R.font.montserrat_extrabolditalic, FontWeight.ExtraBold)
)

val AbrilFatface = FontFamily(
    Font(R.font.abrilfatface_regular)
)

val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = AbrilFatface),
    displayMedium = baseline.displayMedium.copy(fontFamily = AbrilFatface),
    displaySmall = baseline.displaySmall.copy(fontFamily = AbrilFatface),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = AbrilFatface),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = AbrilFatface),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = AbrilFatface),
    titleLarge = baseline.titleLarge.copy(fontFamily = AbrilFatface),
    titleMedium = baseline.titleMedium.copy(fontFamily = AbrilFatface),
    titleSmall = baseline.titleSmall.copy(fontFamily = AbrilFatface),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = Montserrat),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = Montserrat),
    bodySmall = baseline.bodySmall.copy(fontFamily = Montserrat),
    labelLarge = baseline.labelLarge.copy(fontFamily = Montserrat),
    labelMedium = baseline.labelMedium.copy(fontFamily = Montserrat),
    labelSmall = baseline.labelSmall.copy(fontFamily = Montserrat),
)