package com.example.practice.ui.product.order

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.practice.ui.product.address.AddressViewModel

@Composable
fun OrderDetail(navController: NavController, orderViewModel: OrderViewModel, addressViewModel: AddressViewModel) {
    val orderHistory by remember { mutableStateOf(orderViewModel.orderHistory) }
    val address = addressViewModel.address
}