package com.lwsoftware.abletotrack.dto.response

data class LoginResponseDto(
  val exists: Boolean = false,
  val firstName: String = "",
  val lastName: String = "",
  val isEmailVerified: Boolean = false
)