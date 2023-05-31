package com.lwsoftware.abletotrack.dto.response

data class LoginResponseDto(
  val accessToken: String = "",
  val exists: Boolean = false,
  val isEmailVerified: Boolean = false,
  val userId: Long = 0
)