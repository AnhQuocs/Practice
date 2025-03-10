package com.example.practice.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.R
import com.example.practice.ui.account.user.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _userData = MutableStateFlow(UserData("", "", "", "", 0))
    val userData: StateFlow<UserData> = _userData

    fun updateUserData(userName: String, email: String, password: String) {
        viewModelScope.launch {
            _userData.value = UserData(
                userId = System.currentTimeMillis().toString(), // Fake ID
                userName = userName,
                email = email,
                password = password,
                avt = R.drawable.food_2 // Ảnh đại diện mặc định
            )
        }
    }
}
