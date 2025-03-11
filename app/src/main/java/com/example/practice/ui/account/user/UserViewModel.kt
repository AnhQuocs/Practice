package com.example.practice.ui.account.user

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val _users = mutableStateListOf<UserData>()
    val users: List<UserData> get() = _users

    fun addUser(user: UserData) {
        _users.add(user)
        Log.d("frank", "Danh sách users: $_users")
    }

    fun checkUser(email: String, password: String): Boolean {
        return _users.any { it.email == email && it.password == password }
    }
}


