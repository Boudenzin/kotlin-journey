package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import com.example.cupcake.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    // lateinit é usada para declarar uma propriedade que pode ser inicializada após a declaração do objeto.
    private lateinit var navController: TestNavHostController

    private fun navigateToFlavorScreen() {
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        composeTestRule.onNodeWithStringId(R.string.chocolate).performClick()
    }

    // Metodo auxiliar para ir da tela "Flavor" para a tela "Pickup"
    private fun navigateToPickupScreen() {
        // Primeiro, navega para a tela Flavor e seleciona um sabor.
        navigateToFlavorScreen()

        // Clica no botão "Next" (Próximo)
        composeTestRule.onNodeWithStringId(R.string.next).performClick()
    }

    private fun getFormattedDate() :String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()

        composeTestRule.onNodeWithText(getFormattedDate()).performClick()

        composeTestRule.onNodeWithStringId(R.string.next).performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen(){
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_navigate_to_summary_screen() {
        navigateToSummaryScreen()

        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButtonOnFlavorScreen_navigatesToStartScreen() {
        navigateToFlavorScreen()

        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonOnFlavorScreen_navigatesToStartScreen() {
        navigateToFlavorScreen()

        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickUpButtonOnPickupScreen_navigatesToFlavorScreen() {
        navigateToPickupScreen()

        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonOnPickupScreen_navigatesToStartScreen() {
        navigateToPickupScreen()

        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonOnSummaryScreen_navigatesToStartScreen() {
        navigateToSummaryScreen()

        composeTestRule.onNodeWithStringId(R.string.cancel).performClick()

        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
}

