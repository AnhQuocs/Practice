package com.example.practice.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {
    var selectedTab by remember { mutableStateOf("home") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomBarItem(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = selectedTab == "home",
            onClick = {
                if (selectedTab != "home") {
                    selectedTab = "home"
                    navController.navigate("home") {
                        launchSingleTop = true
                        popUpTo("home") { inclusive = true }
                    }
                }
            }
        )

        BottomBarItem(
            icon = Icons.Default.AccessTime,
            label = "History",
            isSelected = selectedTab == "history",
            onClick = {
                if (selectedTab != "history") {
                    selectedTab = "history"
                    navController.navigate("history") {
                        launchSingleTop = true
                        popUpTo("home") { inclusive = true }
                    }
                }
            }
        )

        BottomBarItem(
            icon = Icons.Default.Person,
            label = "Me",
            isSelected = selectedTab == "me",
            onClick = {
                if (selectedTab != "me") {
                    selectedTab = "me"
                    navController.navigate("me") {
                        launchSingleTop = true
                        popUpTo("home") { inclusive = true }
                    }
                }
            }
        )
    }
}

@Composable
fun BottomBarItem(icon: ImageVector, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = label, tint = if(isSelected) Color(0xFF33CC66) else Color.Black)
        Text(label, color = if(isSelected) Color(0xFF33CC66) else Color.Black)
    }
}

