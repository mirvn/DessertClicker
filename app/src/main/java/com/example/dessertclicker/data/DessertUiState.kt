package com.example.dessertclicker.data

import androidx.annotation.DrawableRes

data class DessertUiState(
    val currentIndex: Int = 0,
    val dessertSold: Int = 0,
    val revenue: Int = 0,
    val currentPrice: Int = Datasource.dessertList[currentIndex].price,
    @DrawableRes val dessertImage: Int = Datasource.dessertList[currentIndex].imageId
)
