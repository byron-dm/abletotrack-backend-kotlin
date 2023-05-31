package com.lwsoftware.abletotrack.security

import com.lwsoftware.abletotrack.services.JwtTokenService
import com.lwsoftware.abletotrack.services.JwtUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtRequestFilter : OncePerRequestFilter() {

  @Autowired
  private lateinit var jwtTokenService: JwtTokenService

  @Autowired
  private lateinit var jwtUserDetailsService: JwtUserDetailsService

  @Throws(ServletException::class, IOException::class)
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
    if (request.servletPath.contains("/login")) {
      chain.doFilter(request, response);
      return
    }

    // look for Bearer auth header
    val header = request.getHeader(HttpHeaders.AUTHORIZATION)
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response)
      return
    }

    val token = header.substring(7)
    val username = jwtTokenService.validateTokenAndGetUsername(token)
    if (username == null) {
      // validation failed or token expired
      chain.doFilter(request, response)
      return
    }

    // set user details on spring security context
    val userDetails = jwtUserDetailsService.loadUserByUsername(username)
    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
    SecurityContextHolder.getContext().authentication = authentication

    // continue with authenticated user
    chain.doFilter(request, response)
  }
}