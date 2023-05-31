package com.lwsoftware.abletotrack.services

import com.lwsoftware.abletotrack.repositories.UserRepository
import com.lwsoftware.abletotrack.security.JwtUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class JwtUserDetailsService : UserDetailsService {

  @Autowired
  private lateinit var userRepository: UserRepository

  companion object {
    const val USER = "USER"
    const val ROLE_USER = "ROLE_$USER"
  }

  override fun loadUserByUsername(email: String): JwtUserDetails {
    val user = userRepository.getUser(email)

    return JwtUserDetails(user.email, user.password, Collections.singletonList(SimpleGrantedAuthority(ROLE_USER)), user.id, user.isEmailVerified)
  }
}