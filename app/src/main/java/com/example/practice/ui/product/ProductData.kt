package com.example.practice.ui.product

data class ProductData (
    val id: Int,
    val name: String,
    val category: String,
    val price: Float,
    val image: Int
)

data class OrderItem(
    val product: ProductData,
    var quantity: Int
) {
    val totalPrice: Float
        get() = product.price * quantity
}
