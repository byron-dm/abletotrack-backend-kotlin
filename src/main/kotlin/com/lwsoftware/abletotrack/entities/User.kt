package com.lwsoftware.abletotrack.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="users")
class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0

  @Column(name = "first_name", nullable = false)
  val firstName = ""

  @Column(name = "last_name", nullable = false)
  val lastName = ""

  @Column(name = "email", nullable = false)
  val email = ""

  @Column(name = "password", nullable = false)
  val password = ""

  @Column(name = "is_email_verified", nullable = false)
  var isEmailVerified = 0;

  @Column(name = "should_remember_me", nullable = false)
  var shouldRememberMe = 0;
}