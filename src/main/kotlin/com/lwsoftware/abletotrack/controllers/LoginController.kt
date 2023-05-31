package com.lwsoftware.abletotrack.controllers

import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.security.JwtUserDetails
import com.lwsoftware.abletotrack.services.JwtTokenService
import com.lwsoftware.abletotrack.services.JwtUserDetailsService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class LoginController {

  @Autowired
  private lateinit var authenticationManager: AuthenticationManager
  @Autowired
  private lateinit var jwtTokenService: JwtTokenService
  @Autowired
  private lateinit var jwtUserDetailsService: JwtUserDetailsService

  @PostMapping("/login")
  fun authenticate(@RequestBody @Valid loginRequestDto: LoginRequestDto): LoginResponseDto {
    try {
      authenticationManager.authenticate(
        UsernamePasswordAuthenticationToken(loginRequestDto.email, loginRequestDto.password)
      )
    } catch (exception: BadCredentialsException) {
      throw ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.message);
    }

    val userDetails: JwtUserDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDto.email)
    return LoginResponseDto(
      accessToken = jwtTokenService.generateToken(userDetails),
      exists = true,
      userId = userDetails.userId,
      isEmailVerified = userDetails.isEmailVerified
    )
  }
}