package com.lwsoftware.abletotrack.dto.request

data class LoginRequestDto(val email: String, val password: String, val shouldRememberMe: Boolean = false)