package com.example.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.practice.ui.product.address.AddressViewModel
import com.example.practice.ui.product.order.OrderDetail
import com.example.practice.ui.product.order.OrderScreen
import com.example.practice.ui.product.order.OrderViewModel
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

    val orderViewModel: OrderViewModel = viewModel()
    val addressViewModel: AddressViewModel = viewModel()

    PracticeTheme {
        Column(modifier = modifier.fillMaxSize().padding(top = 12.dp, bottom = 12.dp)) {
            // Điều hướng đến các màn hình trong NavHost
            NavHost(navController = navController, startDestination = "signUp", modifier = modifier.weight(1f)) {
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

                composable(
                    "home",
                    exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },   // Khi chuyển sang History, Home lướt sang trái
                    popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) }, // Khi quay lại từ History, Home lướt từ phải vào
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { -it }) } // Khi chuyển sang Me, Home lướt sang trái
                    ) {
                    HomeScreen(navController)
                }

                composable(
                    "me",
                    enterTransition = { slideInHorizontally(initialOffsetX = { it }) },  // Me trượt từ phải vào
                    exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() }, // Khi sang History, Me trượt sang trái
                    popEnterTransition = { slideInHorizontally(initialOffsetX = { it }) }, // Khi quay lại từ History, Me trượt từ phải vào
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { -it }) + fadeOut() } // Khi quay lại Home, Me trượt sang trái
                    ) {
                    AccountScreen(navController)
                }

                composable(
                    "order/{productId}",
                    arguments = listOf(navArgument("productId") {type = NavType.IntType})
                ) {
                    backStackEntry ->
                    val productId = backStackEntry.arguments?.getInt("productId") ?:0
                    OrderScreen(productId = productId, orderViewModel, addressViewModel)
                }

                composable(
                    "history",
//                    enterTransition = { slideInHorizontally(initialOffsetX = { it }) },  // History lướt từ phải vào
//                    exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },  // Khi sang Me, History lướt sang trái
//                    popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) }, // Khi quay lại từ Me, History lướt từ trái vào
                    popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) }    // Khi quay lại Home, History lướt sang phải
                    ) {
                    HistoryScreen(navController, orderViewModel)
                }

                composable(
                    "detail/{productId}",
                    arguments = listOf(navArgument("productId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                    OrderDetail(navController, orderViewModel, addressViewModel, productId)
                }

            }

            if(currentRoute == "home" || currentRoute == "history" || currentRoute == "me") {
                BottomBar(navController = navController)
            } else {
//                Spacer(modifier = Modifier.height(62.dp))
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