package com.example.gabriel.cryptocurrencies.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CryptocurrencyViewModelFactory @Inject constructor(
    private val cryptocurrenciesViewModel: CryptocurrencyViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptocurrencyViewModel::class.java)) {
            return cryptocurrenciesViewModel as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}