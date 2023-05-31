package com.lwsoftware.abletotrack.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class JwtUserDetails(
  username: String,
  password: String,
  authorities: MutableCollection<out GrantedAuthority>,
  val userId: Long,
  val isEmailVerified: Boolean
) : User(username, password, authorities)