
package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertUiState
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    /**
     * Aqui se cria um valor privado que pode
     * ser modificado e depois eu crio um publico
     * que a UI lê, mas não modifica
     *
     */

    private val primeiraSobremesa = Datasource.dessertList.first()
    private val _uiState = MutableStateFlow(
        DessertUiState(
            currentDessertPrice = primeiraSobremesa.price,
            currentDessertImageId = primeiraSobremesa.imageId

        )
    )

    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    /**
     * Atualiza o estado do jogo quando a sobremesa é clicada.
     */
    fun onDessertClicked() {
       _uiState.update {  currentState ->

           val newRevenue = currentState.revenue + currentState.currentDessertPrice
           val dessertsSold = currentState.dessertsSold + 1

           val dessertsToShow = determineDessertToShow(
               desserts = Datasource.dessertList,
               dessertsSold = dessertsSold
           )

           currentState.copy(
               revenue = newRevenue,
               dessertsSold = dessertsSold,
               currentDessertPrice = dessertsToShow.price,
               currentDessertImageId = dessertsToShow.imageId
           )
       }
    }

    /**
     * Determina qual sobremesa mostrar com base nas sobremesas vendidas.
     */
    private fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }

        return dessertToShow
    }

}