package com.lwsoftware.abletotrack.services

import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.dto.response.RecoverPasswordResponseDto
import com.lwsoftware.abletotrack.extensions.toBoolean
import com.lwsoftware.abletotrack.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class UserService {

  @Autowired
  lateinit var repository: UserRepository

  @Autowired
  lateinit var messageSource: MessageSource

  fun login(request: LoginRequestDto): LoginResponseDto {
    val user = repository.getUser(request.email, request.password, request.shouldRememberMe)

    return if (user == null) {
      LoginResponseDto();
    } else {
      LoginResponseDto(true, user.firstName, user.lastName, user.isEmailVerified.toBoolean())
    }
  }

  fun recoverPassword(email: String): RecoverPasswordResponseDto {
    return RecoverPasswordResponseDto(
      true,
      messageSource.getMessage("service.user.success.emailsent", null, Locale.getDefault())
    )
  }
}