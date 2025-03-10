package com.example.practice.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practice.R
import com.example.practice.ui.product.order.OrderViewModel

@Composable
fun HistoryScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val orderHistory by remember { mutableStateOf(orderViewModel.orderHistory) }
    val wavyFont = FontFamily(Font(R.font.wavy_font))

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Order History",
            fontFamily = wavyFont,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFFF6600)
        )

        if (orderHistory.isEmpty()) {
            Text(
                "No orders yet!",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
            )
        } else {
            LazyColumn {
                items(orderHistory) { order ->
                    OrderItemCard(order)
                }
            }
        }
    }
}

@Composable
fun OrderItemCard(order: OrderItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val (image, name, category, price, total, button)  = createRefs()

            if (order.product.category != "Book") {
                Image(
                    painter = painterResource(id = order.product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp, 95.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .constrainAs(image) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = order.product.image),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(90.dp)
                        .constrainAs(image) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                )
            }

            Text(
                text = "${order.product.name} (x${order.quantity})",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(name) {
                    start.linkTo(image.end, margin = 8.dp)
                    top.linkTo(parent.top)
                }
            )

            Text(
                text = order.product.category,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.constrainAs(category) {
                    start.linkTo(name.start)
                    top.linkTo(name.bottom, margin = 4.dp)
                }
            )

            Text(
                text = "Price: $${order.product.price}",
                modifier = Modifier.constrainAs(price) {
                    start.linkTo(name.start)
                    top.linkTo(category.bottom, margin = 4.dp)
                }
            )

            Text(
                text = "Total: $${order.totalPrice}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(total) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
                modifier = Modifier
                    .wrapContentSize()
                    .width(80.dp)
                    .height(32.dp)
                    .constrainAs(button) {
                        bottom.linkTo(price.bottom, margin = 18.dp)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    "View",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryPreview() {
    val navController = rememberNavController()
    val orderViewModel = remember { OrderViewModel() }

    // Thêm dữ liệu bằng hàm addOrder()
    orderViewModel.addOrder(ProductData(1, "Noodle", "Food", 1.99f, R.drawable.food_2), 2)

    HistoryScreen(navController, orderViewModel)
}

