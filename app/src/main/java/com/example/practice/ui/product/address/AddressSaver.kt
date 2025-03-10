package com.example.practice.ui.product.address

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable

data class Address(
    var country: String = "",
    var city: String = "",
    var district: String = "",
    var ward: String = "",
    var concrete: String = ""
)

val AddressSaver = object : Saver<Address, List<String>> {
    override fun restore(value: List<String>): Address {
        return Address(
            country = value[0],
            city = value[1],
            district = value[2],
            ward = value[3],
            concrete = value[4]
        )
    }

    override fun SaverScope.save(value: Address): List<String> {
        return listOf(value.country, value.city, value.district, value.ward, value.concrete)
    }
}

