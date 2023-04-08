package com.lwsoftware.abletotrack.dto.response

data class RecoverPasswordResponseDto(
  val emailSent: Boolean = false,
  val message: String = ""
)