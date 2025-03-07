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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun SignUpScreen(signUpClick: () -> Unit, navLogIn: () -> Unit) {
    var user by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var reEnterPassword by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.picwhite),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            WelcomeAboard()
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
        ) {
            UserNameTextField(user = user, onUserChange = {user = it})
            EmailTextField(email = email, onEmailChange = {email = it})
            PasswordTextField(password = password, onPasswordChange = {password = it})
            ReEnterPasswordTextField(password = reEnterPassword, onPasswordChange = {reEnterPassword = it})
            Spacer(modifier = Modifier.height(28.dp))
            SignUpButton(signUpClick)
            BackSignIn(navLogIn)
        }
    }
}

@Composable
fun UserNameTextField(user: String, onUserChange: (String) -> Unit) {
    Column() {
        Text(
            "User Name",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(0.dp, 28.dp, 0.dp, 4.dp)
        )

        OutlinedTextField(
            value = user,
            onValueChange = onUserChange,
            label = { Text("User Name", color = Color.Gray)},
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
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
fun ReEnterPasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    var isShowPassword by remember { mutableStateOf(false) }
    Column {
        Text(
            "Re-enter the password",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(0.dp, 28.dp, 0.dp, 4.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Re-enter the password", color = Color.Gray)},
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
fun WelcomeAboard(modifier: Modifier = Modifier) {
    Text(
        "Welcome Aboard",
        fontSize = 46.sp,
        color = Color(0xFF33CC66)
    )
}

@Composable
fun SignUpButton(signUpClick: () -> Unit) {
    Button(
        onClick = {signUpClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF33CC66)
        )
    ) {
        Text(
            "Sign Up",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier.padding(0.dp, 8.dp).fillMaxWidth()
        )
    }
}

@Composable
fun BackSignIn(navLogIn: () -> Unit) {
    Row (
        modifier = Modifier.padding(0.dp, 24.dp)
    ) {
        Text("Already have an account? ", fontSize = 18.sp)
        Text("Log in",
            fontSize = 18.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            color = Color(0xFF33CC66),
            modifier = Modifier.clickable { navLogIn() }
        )
    }
}