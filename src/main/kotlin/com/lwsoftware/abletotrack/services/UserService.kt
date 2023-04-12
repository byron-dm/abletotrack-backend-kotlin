package com.lwsoftware.abletotrack.services

import com.lwsoftware.abletotrack.dto.request.LoginRequestDto
import com.lwsoftware.abletotrack.dto.response.LoginResponseDto
import com.lwsoftware.abletotrack.dto.response.RecoverPasswordResponseDto
import com.lwsoftware.abletotrack.dto.response.UserProfileResponseDto
import com.lwsoftware.abletotrack.dto.response.UserResponseDto
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
      LoginResponseDto()
    } else {
      LoginResponseDto(true, user.isEmailVerified.toBoolean(), user.id)
    }
  }

  fun recoverPassword(email: String): RecoverPasswordResponseDto {
    return RecoverPasswordResponseDto(
      true,
      messageSource.getMessage("Service.User.Success.EmailSent", null, Locale.getDefault())
    )
  }

  fun getUser(userId: Long): UserResponseDto {
    val user = repository.getUser(userId);

    return UserResponseDto(user.firstName, user.lastName, UserProfileResponseDto(user.profile.picture))
  }
}