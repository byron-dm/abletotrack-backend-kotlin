package com.lwsoftware.abletotrack.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.PrimaryKeyJoinColumn
import jakarta.persistence.Table

@Entity
@Table(name="users")
class User {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = 0

  @Column(name = "EMAIL", nullable = false)
  var email = ""

  @Column(name = "PASSWORD", nullable = false)
  var password = ""

  @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
  var isEmailVerified = false

  @Column(name = "SHOULD_REMEMBER_ME", nullable = false)
  var shouldRememberMe = false

  @OneToOne
  @PrimaryKeyJoinColumn
  lateinit var profile: UserProfile
}