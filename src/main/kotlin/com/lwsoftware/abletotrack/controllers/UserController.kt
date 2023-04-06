package com.lwsoftware.abletotrack.controllers

import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

  @Autowired
  private lateinit var userService: UserService;

  @PostMapping("/login")
  @ResponseBody
  fun login(@RequestBody request: LoginRequestDto): LoginResponseDto {
    return userService.login(request)
  }
}