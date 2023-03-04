package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
class DessertViewModel: ViewModel() {
    private val _dessertState = MutableStateFlow(DessertUiState())
    val dessertState = _dessertState.asStateFlow()


    fun setDessertOnClick() {
        _dessertState.update { currentState ->
            val dessertSold = currentState.dessertSold + 1
            val nextIndex = determineDessertToShow(dessertSold)
            currentState.copy(
                currentIndex = nextIndex,
                dessertSold = dessertSold,
                revenue = currentState.revenue + currentState.currentPrice,
                currentPrice =  Datasource.dessertList[nextIndex].price,
                dessertImage = Datasource.dessertList[nextIndex].imageId
            )
        }
    }


    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow(
        dessertsSold: Int
    ): Int {
        var nextIndex = 0
        for (index in Datasource.dessertList.indices) {
            if (dessertsSold >= Datasource.dessertList[index].startProductionAmount) {
                nextIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return nextIndex
    }

}