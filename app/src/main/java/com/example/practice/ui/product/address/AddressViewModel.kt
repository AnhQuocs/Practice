package com.example.practice.ui.product.address

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddressViewModel : ViewModel() {
    private val _addresses = MutableStateFlow<Map<Int, Address>>(emptyMap())
    val addresses: StateFlow<Map<Int, Address>> = _addresses

    fun updateAddress(productId: Int, newAddress: Address) {
        _addresses.value = _addresses.value.toMutableMap().apply {
            this[productId] = newAddress
        }
    }

    fun getAddress(productId: Int): Address {
        return _addresses.value[productId] ?: Address("Unknown", "Unknown", "Unknown", "Unknown", "Unknown")
    }
}
