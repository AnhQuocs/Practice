package com.example.practice

import com.example.practice.ui.navigation.BottomBar
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practice.ui.account.LoginScreen
import com.example.practice.ui.account.SignUpScreen
import com.example.practice.ui.home.HomeScreen
import com.example.practice.ui.account.AccountScreen
import com.example.practice.ui.product.HistoryScreen
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
                    HomeScreen(navController) // Đảm bảo HomeScreen là một Composable và gọi từ đây
                }

                composable("history") {
                    HistoryScreen()
                }

                composable("me") {
                    AccountScreen()
                }
            }

            // BottomBar luôn hiển thị ở dưới cùng
            BottomBar(navController = navController)
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