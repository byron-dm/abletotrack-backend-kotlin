package com.lwsoftware.abletotrack.controllers

import com.lwsoftware.abletotrack.dto.response.RecoverPasswordResponseDto
import com.lwsoftware.abletotrack.dto.response.UserProfileResponseDto
import com.lwsoftware.abletotrack.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {

  @Autowired
  private lateinit var userService: UserService;

  @PostMapping("/recover-password")
  @ResponseBody
  fun recoverPassword(@RequestBody email: String): RecoverPasswordResponseDto {
    return userService.recoverPassword(email)
  }

  @PostMapping("/get-profile")
  @ResponseBody
  fun getProfile(@RequestBody userId: Long): UserProfileResponseDto {
    return userService.getProfile(userId)
  }
}