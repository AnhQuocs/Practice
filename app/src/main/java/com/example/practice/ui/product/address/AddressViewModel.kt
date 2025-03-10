package com.example.practice.ui.product.address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddressViewModel : ViewModel() {
    var address by mutableStateOf(Address())
    private set

    fun updateAddress(newAddress: Address) {
        address = newAddress
    }
}