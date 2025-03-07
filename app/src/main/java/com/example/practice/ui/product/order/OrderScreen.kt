package com.example.practice.ui.product.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.practice.R
import com.example.practice.ui.product.ProductData

@Composable
fun OrderScreen(productId: Int) {
    val productList = listOf(
        ProductData(20, "BABI YAR", "Book", 9.99f, R.drawable.book_2),
        ProductData(2, "Pancakes", "Food", 3.99f, R.drawable.food_1),
        ProductData(10, "Long coat", "Clothes", 69.99f, R.drawable.clothes_4),
        ProductData(18, "LGT Keyboard", "Electronics", 49.99f, R.drawable.electronies_6),
        ProductData(5, "Noodle", "Food", 3.75f, R.drawable.food_4),
        ProductData(7, "Vest", "Clothes", 29.99f, R.drawable.clothes_1),
        ProductData(15, "LGT Mouse", "Electronics", 14.99f, R.drawable.electronies_3),
        ProductData(8, "Polka-dot shirt", "Clothes", 19.99f, R.drawable.clothes_2),
        ProductData(1, "Hủ Tiếu", "Food", 4.50f, R.drawable.food_6),
        ProductData(16, "LGT Mouse 2", "Electronics", 18.99f, R.drawable.electronies_4),
        ProductData(9, "Blazer", "Clothes", 49.99f, R.drawable.clothes_3),
        ProductData(11, "Slip Dress", "Clothes", 39.99f, R.drawable.clothes_5),
        ProductData(21, "LEADING", "Book", 14.99f, R.drawable.book_3),
        ProductData(4, "Salad", "Food", 4.25f, R.drawable.food_3),
        ProductData(12, "Shirts", "Clothes", 15.99f, R.drawable.clothes_6),
        ProductData(14, "ViewSonic", "Electronics", 199.99f, R.drawable.electronies_2),
        ProductData(22, "LEPRECHAUN", "Book", 10.99f, R.drawable.book_4),
        ProductData(13, "LG UltraWide", "Electronics", 299.99f, R.drawable.electronies_1),
        ProductData(6, "Caesar Salad", "Food", 5.50f, R.drawable.food_5),
        ProductData(19, "THE SHOWMAN", "Book", 12.99f, R.drawable.book_1),
        ProductData(17, "EDRA Keyboard", "Electronics", 34.99f, R.drawable.electronies_5),
        ProductData(3, "Fried Fish", "Food", 6.50f, R.drawable.food_2)
    )
    val product = productList.find { it.id == productId } ?: return

    OrderItems(product)
}

@Composable
fun OrderItems(product: ProductData) {
    val countQuantity = remember { mutableStateOf("1") }
    val totalPrice = remember { mutableStateOf(product.price) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                if(product.category != "Book") {
                    Image(
                        painter = rememberAsyncImagePainter(model = product.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(180.dp, 155.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = product.image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(20.dp))
                    )
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
                if(product.category == "Electronics") {
                    Row {
                        Text("Name: ", fontSize = 16.sp)
                        Text("${product.name}", fontSize = 16.sp, color = Color(0xFFFF9800))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text("Category: ", fontSize = 16.sp)
                        Text("${product.category}", fontSize = 16.sp, color = Color(0xFFFF9800))
                    }
                } else {
                    Row {
                        Text("Name: ", fontSize = 18.sp)
                        Text("${product.name}", fontSize = 18.sp, color = Color(0xFFFF9800))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text("Category: ", fontSize = 18.sp)
                        Text("${product.category}", fontSize = 18.sp, color = Color(0xFFFF9800))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text("Price: ", fontSize = 18.sp)
                    Text("$${product.price}", fontSize = 18.sp, color = Color(0xFFFF9800))
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Quantity: ", fontSize = 18.sp)

                    Button(
                        onClick = {
                            val current = countQuantity.value.toInt()
                            if (current > 1) {
                                countQuantity.value = (current - 1).toString()
                                totalPrice.value = countQuantity.value.toInt() * product.price
                            }
                        },
                        modifier = Modifier
                            .size(25.dp)
                            .clip(RoundedCornerShape(50)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF))
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("-", fontSize = 22.sp, textAlign = TextAlign.Center)
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text("${countQuantity.value}", fontSize = 18.sp, modifier = Modifier.width(30.dp), color = Color(0xFFFF9800))

                    Button(
                        onClick = {
                            val current = countQuantity.value.toInt()
                            countQuantity.value = (current + 1).toString()
                            totalPrice.value = countQuantity.value.toInt() * product.price
                        },
                        modifier = Modifier
                            .size(25.dp)
                            .clip(RoundedCornerShape(50)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF))
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("+", fontSize = 20.sp, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        var country by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var district by remember { mutableStateOf("") }
        var ward by remember { mutableStateOf("") }
        var concrete by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            if(product.category == "Food") {
                OrderTextField("City", city, onValueChange = { city = it }, "City", Icons.Default.LocationOn)
                OrderTextField("District", district, onValueChange = { district = it }, "District", Icons.Default.LocationOn)
                OrderTextField("Ward", ward, onValueChange = { ward = it }, "Ward", Icons.Default.LocationOn)
                OrderTextField("Specific address", concrete, onValueChange = { concrete = it }, "Specific address", Icons.Default.LocationOn)
            } else {
                OrderTextField("Country", country, onValueChange = { country = it }, "Country", Icons.Default.LocationOn)
                OrderTextField("City", city, onValueChange = { city = it }, "City", Icons.Default.LocationOn)
                OrderTextField("District", district, onValueChange = { district = it }, "District", Icons.Default.LocationOn)
                OrderTextField("Ward", ward, onValueChange = { ward = it }, "Ward", Icons.Default.LocationOn)
                OrderTextField("Specific address", concrete, onValueChange = { concrete = it }, "Specific address", Icons.Default.LocationOn)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Total: $${String.format("%.2f", totalPrice.value)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
                .width(120.dp)
        ) {
            Text(text = "Order", fontSize = 18.sp)
        }
    }
}

@Composable
fun OrderTextField(text: String, value: String, onValueChange: (String) -> Unit, label: String, icon: ImageVector) {
    Column {
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(0.dp, 18.dp, 0.dp, 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label)},
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewOrderScreen() {
//    OrderScreen(productId = 10) // Chọn một productId để xem trước
//}