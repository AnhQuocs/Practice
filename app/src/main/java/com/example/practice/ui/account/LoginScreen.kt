package com.example.practice.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice.R

@Composable
fun LoginScreen(loginClick: () -> Unit, navSignUp: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.picwhite),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            WelcomeText()
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
        ) {
            EmailTextField(email = email, onEmailChange = { email = it })
            PasswordTextField(password = password, onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(28.dp))
            LoginButton(loginClick)
            NewAccount(navSignUp)
        }
    }
}

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Text(
        "Welcome Back",
        fontSize = 52.sp,
        color = Color(0xFF33CC66)
    )
}

@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
    Column() {
        Text(
            "Email Address",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(0.dp, 28.dp, 0.dp, 4.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email address", color = Color.Gray)},
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "",
                    tint = Color(0xFF33CC66)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )
    }
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    var isShowPassword by remember { mutableStateOf(false) }
    Column {
        Text(
            "Password",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(0.dp, 28.dp, 0.dp, 4.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password", color = Color.Gray)},
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "",
                    tint = Color(0xFF33CC66)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    isShowPassword = !isShowPassword
                }) {
                    Icon(
                        if (isShowPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = "",
                        tint = Color(0xFF33CC66)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            visualTransformation = if(isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

@Composable
fun LoginButton(loginClick: () -> Unit) {
    Button(
        onClick = {loginClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF33CC66)
        )
    ) {
        Text(
            "Login",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(0.dp, 8.dp).fillMaxWidth()
        )
    }
}

@Composable
fun NewAccount(navSignUp: () -> Unit) {
    Row (
        modifier = Modifier.padding(0.dp, 24.dp)
    ) {
        Text("Don't have an account? ", fontSize = 18.sp)
        Text("Sign up",
            fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            color = Color(0xFF33CC66),
            modifier = Modifier.clickable { navSignUp() }
        )
    }
}