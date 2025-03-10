package com.example.practice.ui.product.order

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.practice.R
import com.example.practice.ui.product.address.AddressViewModel

@Composable
fun OrderDetail(navController: NavController, orderViewModel: OrderViewModel, addressViewModel: AddressViewModel, productId: Int) {

    val orderHistory = orderViewModel.orderHistory
    val address by remember { derivedStateOf { addressViewModel.getAddress(productId) } }

    val product = orderHistory.find { it.product.id == productId }?.product
    val totalQuantity = orderViewModel.orderHistory
        .filter { it.product.id == productId }
        .sumOf { it.quantity }

    val totalPrice = orderViewModel.orderHistory
        .filter { it.product.id == productId }
        .sumOf { it.totalPrice.toDouble() }
        .toFloat()

    val wavyFont = FontFamily(Font(R.font.wavy_font))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White) // Đảm bảo nền che phần viền bị che mất
                .padding(12.dp)
        ) {
            Text(
                "Product Detail",
                fontSize = 28.sp,
                fontFamily = wavyFont,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFF6600)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .clip(RoundedCornerShape(20.dp))
                .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(20.dp))
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (product != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = product.image),
                        contentDescription = null,
                        contentScale = if (product.category == "Book") ContentScale.Fit else ContentScale.Crop,
                        modifier = Modifier
                            .size(if (product.category == "Book") 150.dp else 180.dp, 155.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                } else {
                    Text("No product available", color = Color.Gray)
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(20.dp))
                    .padding(start = 12.dp, 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (product != null) {
                    Row {
                        Text("Name: ", fontSize = 16.sp)
                        Text("${product.name}", fontSize = 16.sp, color = Color(0xFFFF9800))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text("Category: ", fontSize = 16.sp)
                        Text("${product.category}", fontSize = 16.sp, color = Color(0xFFFF9800))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text("Price: ", fontSize = 18.sp)
                        Text("$${product.price}", fontSize = 18.sp, color = Color(0xFFFF9800))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text("Quantity: ", fontSize = 18.sp)
                        Text("${totalQuantity}", fontSize = 18.sp, color = Color(0xFFFF9800))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        Text("Total: ", fontSize = 18.sp)
                        Text("$${totalPrice}", fontSize = 18.sp, color = Color(0xFFFF9800))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Address",
            fontSize = 20.sp,
            fontFamily = wavyFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFFF6600)
        )

        Column(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 24.dp)
        ) {
            if (product != null) {
                if (product.category != "Food") {
                    Row {
                        Text("Country: ", fontSize = 18.sp, color = Color(0xFF33CC66))
                        Text("${address.country}", fontSize = 18.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text("City: ", fontSize = 18.sp, color = Color(0xFF33CC66))
                    Text("${address.city}", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text("District: ", fontSize = 18.sp, color = Color(0xFF33CC66))
                    Text("${address.district}", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text("Ward: ", fontSize = 18.sp, color = Color(0xFF33CC66))
                    Text("${address.ward}", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text("Specific address: ", fontSize = 18.sp, color = Color(0xFF33CC66))
                    Text("${address.concrete}", fontSize = 18.sp)
                }
            }
        }

        Button(
            onClick = {navController.popBackStack()},
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
                .width(120.dp)
        ) {
            Text("< Back", fontSize = 16.sp)
        }
    }

    LaunchedEffect(Unit) {
        Log.d("AddressViewModel", "Updating address: $address")
    }
}


