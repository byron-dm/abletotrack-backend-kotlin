package com.lwsoftware.abletotrack.security

import com.lwsoftware.abletotrack.services.JwtUserDetailsService.Companion.USER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class JwtSecurityConfig {

  @Autowired
  private lateinit var jwtRequestFilter: JwtRequestFilter

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  @Throws(Exception::class)
  fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
    return authenticationConfiguration.authenticationManager
  }

  @Bean
  @Throws(Exception::class)
  fun configure(http: HttpSecurity): SecurityFilterChain {

    return http.cors()
      .and()
      .csrf().disable()
      .authorizeHttpRequests().requestMatchers("/", "/login").permitAll()
      .anyRequest().hasRole(USER)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
      .build()
  }
}