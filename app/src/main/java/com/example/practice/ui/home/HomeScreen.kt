package com.example.practice.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practice.R
import com.example.practice.ui.product.ProductData
import com.example.practice.ui.product.ProductList

@Composable
fun HomeScreen(navController: NavController) {

    val wavyFont =FontFamily(Font(R.font.wavy_font))

    // Quản lý danh mục đang chọn ở HomeScreen
    var selectedCategory by remember { mutableStateOf("All") }

    Column{
        Box(
            modifier = Modifier.weight(0.13f)
        ) {
            Text("Shopoo",
                fontFamily = wavyFont,
                fontSize = 58.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFFF6600)
            )
        }

        Column (modifier = Modifier.weight(0.9f)) {
            FilterCategory(
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category

                }
            )

            val allProducts = remember {
                listOf(
                    ProductData(20, "BABI YAR", "Book", 9.99f, R.drawable.book_2),
                    ProductData(2, "Pancakes", "Food", 3.99f, R.drawable.food_1),
                    ProductData(10, "Long coat", "Clothes", 69.99f, R.drawable.clothes_4),
                    ProductData(18, "Logitech Keyboard", "Electronics", 49.99f, R.drawable.electronies_6),
                    ProductData(5, "Noodle", "Food", 3.75f, R.drawable.food_4),
                    ProductData(7, "Vest", "Clothes", 29.99f, R.drawable.clothes_1),
                    ProductData(15, "Logitech Mouse", "Electronics", 14.99f, R.drawable.electronies_3),
                    ProductData(8, "Polka-dot shirt", "Clothes", 19.99f, R.drawable.clothes_2),
                    ProductData(1, "Hủ Tiếu", "Food", 4.50f, R.drawable.food_6),
                    ProductData(16, "Logitech Mouse 2", "Electronics", 18.99f, R.drawable.electronies_4),
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
            }

            val filteredProducts = if(selectedCategory == "All") {
                allProducts
            } else {
                allProducts.filter { it.category == selectedCategory }
            }

            ProductList(products = filteredProducts, navController)
        }
    }
}

@Composable
fun FilterCategory(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listCategories = listOf("All", "Food", "Clothes", "Electronics", "Book")

    LazyRow(modifier = Modifier) {
        items(listCategories) { category ->
            val isSelected = selectedCategory == category

            FilterChip(
                selected = isSelected,
                onClick = { onCategorySelected(category) },
                label = { Text(category, fontSize = 16.sp) },
                modifier = Modifier.padding(8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color.Black,
                    selectedLabelColor = Color.White,
                    containerColor = Color.White,
                    labelColor = Color.Black
                )
            )
        }
    }
}

