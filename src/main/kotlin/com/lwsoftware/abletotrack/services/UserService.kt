package com.lwsoftware.abletotrack.services

import com.lwsoftware.abletotrack.dto.response.RecoverPasswordResponseDto
import com.lwsoftware.abletotrack.dto.response.UserProfileResponseDto
import com.lwsoftware.abletotrack.extensions.toBase64
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

  fun recoverPassword(email: String): RecoverPasswordResponseDto {
    return RecoverPasswordResponseDto(
      true,
      messageSource.getMessage("Service.User.Success.EmailSent", null, Locale.getDefault())
    )
  }

  fun getProfile(userId: Long): UserProfileResponseDto {
    val user = repository.getUser(userId)

    return UserProfileResponseDto(user.profile.firstName, user.profile.lastName, user.profile.picture.toBase64())
  }
}