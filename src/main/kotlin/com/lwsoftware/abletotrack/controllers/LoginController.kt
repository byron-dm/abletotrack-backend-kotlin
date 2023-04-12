package com.lwsoftware.abletotrack.controllers

import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.dto.response.RecoverPasswordResponseDto
import com.lwsoftware.abletotrack.dto.response.UserResponseDto
import com.lwsoftware.abletotrack.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class LoginController {

  @Autowired
  private lateinit var userService: UserService;

  @PostMapping("/login")
  @ResponseBody
  fun login(@RequestBody request: LoginRequestDto): LoginResponseDto {
    return userService.login(request)
  }

  @PostMapping("/recover-password")
  @ResponseBody
  fun recoverPassword(@RequestBody email: String): RecoverPasswordResponseDto {
    return userService.recoverPassword(email)
  }

  @GetMapping("/get-user")
  @ResponseBody
  fun getProfile(@RequestBody userId: Long): UserResponseDto {
    return userService.getUser(userId)
  }
}