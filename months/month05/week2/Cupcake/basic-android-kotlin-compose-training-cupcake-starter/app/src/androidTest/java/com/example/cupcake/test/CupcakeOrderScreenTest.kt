package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.data.DataSource.quantityOptions
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startScreen_verifyContent() {

        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = quantityOptions,
                onNextButtonClicked =  {})
        }

        composeTestRule.onNodeWithStringId(R.string.app_name).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.one_cupcake).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.six_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.twelve_cupcakes).assertIsDisplayed()

    }

    @Test
    fun summaryScreen_verifyContent() {
        val orderState = OrderUiState(
            quantity = 6,
            flavor =   "Chocolate",
            date = "Wed Sep 25",
            price = "$18.00"
        )

        val priceText = composeTestRule.activity.getString(R.string.total_price, orderState.price)

        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState =  orderState,
                onCancelButtonClicked = {},
                onSendButtonClicked = {quantity: String, flavor: String ->}
            )
        }
        // 3. Verifica se as informações do pedido (sabor e quantidade) estão visíveis
        composeTestRule.onNodeWithText(orderState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(orderState.date).assertIsDisplayed()

        // 4. Verifica se o preço total está visível
        composeTestRule.onNodeWithText(priceText).assertIsDisplayed()

        // 5. Verifica se os botões "Send Order" e "Cancel" estão visíveis
        composeTestRule.onNodeWithStringId(R.string.send).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
    }


    // NOVO TESTE para verificar o estado do botão
    @Test
    fun selectOptionScreen_verifyNextButtonIsEnabled() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        // 1. Carrega a tela
        composeTestRule.setContent {
            // Usamos uma função vazia aqui, pois não vamos testar a navegação, apenas o estado do botão
            SelectOptionScreen(subtotal = subtotal, options = flavors, onSelectionChanged = {})
        }

        // 2. Clica em uma das opções (o sabor "Vanilla" ou qualquer outro)
        composeTestRule.onNodeWithText("Vanilla").performClick()

        // 3. Verifica se o botão "Next" AGORA está HABILITADO
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }

}