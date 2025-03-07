package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practice.ui.account.AccountScreen
import com.example.practice.ui.account.LoginScreen
import com.example.practice.ui.account.SignUpScreen
import com.example.practice.ui.home.HomeScreen
import com.example.practice.ui.navigation.BottomBar
import com.example.practice.ui.product.HistoryScreen
import com.example.practice.ui.product.order.OrderScreen
import com.example.practice.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // Tạo navController cho điều hướng
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route // Lấy route hiện tại
    PracticeTheme {
        Column(modifier = modifier.fillMaxSize().padding(top = 12.dp, bottom = 24.dp)) {
            // Điều hướng đến các màn hình trong NavHost
            NavHost(navController = navController, startDestination = "home", modifier = modifier.weight(1f)) {
                composable("login") {
                    LoginScreen(
                        loginClick = { navController.navigate("home") },
                        navSignUp = { navController.navigate("signUp") }
                    )
                }

                composable("signUp") {
                    SignUpScreen(
                        signUpClick = { navController.navigate("home") },
                        navLogIn = { navController.navigate("login") }
                    )
                }

                composable("home") {
                    HomeScreen(navController)
                }

                composable("history") {
                    HistoryScreen()
                }

                composable("me") {
                    AccountScreen()
                }

                composable(
                    "orderScreen/{productId}",
                    arguments = listOf(navArgument("productId") {type = NavType.IntType})
                ) {
                    backStackEntry ->
                    val productId = backStackEntry.arguments?.getInt("productId") ?:0
                    OrderScreen(productId = productId)
                }
            }

            // BottomBar luôn hiển thị ở dưới cùng

            if(currentRoute == "home" || currentRoute == "history" || currentRoute == "me") {
                BottomBar(navController = navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeTheme {
        MainApp()
    }
}