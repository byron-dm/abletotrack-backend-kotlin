package com.lwsoftware.abletotrack.services

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class JwtTokenService(@Value("\${jwt.secret}") secret: String) {

  private val hmac512: Algorithm
  private val verifier: JWTVerifier

  companion object {
    private val JWT_TOKEN_VALIDITY: Duration = Duration.ofMinutes(5)
  }

  init {
    hmac512 = Algorithm.HMAC512(secret)
    verifier = JWT.require(hmac512).build()
  }

  fun generateToken(userDetails: UserDetails): String {
    val now = Instant.now()

    return JWT.create()
      .withSubject(userDetails.username)
      .withIssuer("AbleToTrack")
      .withIssuedAt(now)
      .withExpiresAt(now.plus(JWT_TOKEN_VALIDITY))
      .sign(hmac512)
  }

  fun validateTokenAndGetUsername(token: String?): String? {
    return try {
      verifier.verify(token).subject
    } catch (verificationException: JWTVerificationException) {
      verificationException.printStackTrace()
      //log.warn("token invalid: {}", verificationException.message)
      null
    }
  }
}