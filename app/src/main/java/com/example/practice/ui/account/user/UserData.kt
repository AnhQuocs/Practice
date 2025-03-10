package com.example.practice.ui.account.user

data class UserData (
    val userId: String = "",
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val avt: Int = 0  // Nếu `avt` là ảnh từ `R.drawable`, cần kiểm tra ID hợp lệ.
)
