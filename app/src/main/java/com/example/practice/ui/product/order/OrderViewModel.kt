package com.example.practice.ui.product.order

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.practice.ui.product.OrderItem
import com.example.practice.ui.product.ProductData

class OrderViewModel : ViewModel() {
    private val _orderHistory = mutableStateListOf<OrderItem>()
    val orderHistory: List<OrderItem> get() = _orderHistory

    fun addOrder(product: ProductData, quantity: Int) {
        val existingOrder = _orderHistory.find { it.product.id == product.id }

        if (existingOrder != null) {
            existingOrder.quantity += quantity
        } else {
            _orderHistory.add(OrderItem(product, quantity))
        }
    }
}

