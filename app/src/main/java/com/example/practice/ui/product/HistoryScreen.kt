package com.example.practice.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practice.ui.product.order.OrderViewModel

@Composable
fun HistoryScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val orderHistory by remember { mutableStateOf(orderViewModel.orderHistory) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Order History", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        if (orderHistory.isEmpty()) {
            Text("No orders yet!", fontSize = 18.sp, color = Color.Gray)
        } else {
            LazyColumn {
                items(orderHistory) { order ->
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Column() {
                            Text("${order.product.name} (x${order.quantity})")
                            Text("Price: $${order.product.price} each")
                            Text("Total: $${order.totalPrice}", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
